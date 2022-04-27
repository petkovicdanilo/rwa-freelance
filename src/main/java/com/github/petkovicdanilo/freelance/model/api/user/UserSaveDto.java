package com.github.petkovicdanilo.freelance.model.api.user;

import com.github.petkovicdanilo.freelance.model.common.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Value
@Builder
@Schema(name = "UserSave")
public class UserSaveDto {

    @Schema(description = "User's first name", example = "Pera")
    @NotBlank
    @Size(max = 20)
    String firstName;

    @Schema(description = "User's last name", example = "Peric")
    @NotBlank
    @Size(max = 20)
    String lastName;

    @Schema(description = "User's email. Must be unique", example = "pera.peric@example.com")
    @Email
    @NotBlank
    String email;

    @Schema(description = "User's password", example = "password123")
    @NotBlank
    @Size(min = 8, max = 50)
    String password;

    @Builder.Default
    @Schema(description = "User's gender", example = "MALE")
    Gender gender = Gender.UNKNOWN;

    @Schema(description = "Ids of technologies associated with user")
    @NotEmpty
    List<Integer> technologyIds;
}
