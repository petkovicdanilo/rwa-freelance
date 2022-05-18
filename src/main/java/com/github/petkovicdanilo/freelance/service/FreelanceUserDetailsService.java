package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.mapper.UsersMapper;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreelanceUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email)
                .map(usersMapper::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));
    }
}
