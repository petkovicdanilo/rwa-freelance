package com.github.petkovicdanilo.freelance.model.api.user;

import com.github.petkovicdanilo.freelance.model.common.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
@Schema(name = "UserSave")
public class UserSaveDto {

    @Schema(description = "User's first name", example = "Pera")
    String firstName;

    @Schema(description = "User's last name", example = "Peric")
    String lastName;

    @Schema(description = "User's email. Must be unique", example = "pera.peric@example.com")
    String email;

    @Schema(description = "User's password", example = "password123")
    String password;

    @Builder.Default
    @Schema(description = "User's gender", example = "MALE")
    Gender gender = Gender.UNKNOWN;
}
