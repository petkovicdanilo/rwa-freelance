package com.github.petkovicdanilo.freelance.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;
import com.github.petkovicdanilo.freelance.service.FreelanceUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final FreelanceUserDetailsService userDetailsService;

    private final ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                // swagger
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/users/{id}")
                    .access("@usersGuard.checkUserId(authentication, #id)")
                // jobs
                .antMatchers("/jobs/{id}")
                    .access("@jobsGuard.checkJobId(authentication, #id, true)")
                // technologies
                .antMatchers("/technologies/**").hasRole("ADMIN")
                // applications
                .antMatchers("/jobs/{jobId}/applications/{userId}")
                    .access("@usersGuard.checkUserId(authentication, #userId)")
                .antMatchers("/jobs/{jobId}/applications/{userId}/accept")
                    .access("@jobsGuard.checkJobId(authentication, #jobId, false)")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(((request, response, authException) -> {
                    ErrorInfo errorInfo = ErrorInfo.builder()
                            .errorType(ErrorInfo.ErrorType.AUTHENTICATION)
                            .resourceType(ErrorInfo.ResourceType.ACCESS)
                            .message("Failed to authenticate user. Bad username and/or password")
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.setHeader("WWW-Authenticate", "Basic");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                }))
                .and()
                .exceptionHandling()
                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String message = "User " + (auth != null ? auth.getName() : "'unknown'") +
                            " attempted to access the protected URL: " + request.getRequestURI();

                    ErrorInfo errorInfo = ErrorInfo.builder()
                            .errorType(ErrorInfo.ErrorType.UNAUTHORIZED)
                            .resourceType(ErrorInfo.ResourceType.ACCESS)
                            .message(message)
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                }));

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
