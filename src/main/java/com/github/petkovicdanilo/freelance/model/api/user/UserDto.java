package com.github.petkovicdanilo.freelance.model.api.user;

import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.common.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Schema(name = "User")
public class UserDto {

    @Schema(description = "User identifier", example = "1")
    int id;

    @Schema(description = "User's first name", example = "Pera")
    String firstName;

    @Schema(description = "User's last name", example = "Peric")
    String lastName;

    @Schema(description = "User's email. Must be unique", example = "pera.peric@example.com")
    String email;

    @Builder.Default
    @Schema(description = "User's gender", example = "MALE")
    Gender gender = Gender.UNKNOWN;

    @Schema(description = "Technologies associated with user")
    List<TechnologyDto> technologies;
}
