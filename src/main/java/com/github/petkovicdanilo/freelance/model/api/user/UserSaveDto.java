package com.github.petkovicdanilo.freelance.model.api.user;

import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class UserSaveDto {

    String firstName;

    String lastName;

    String email;

    String password;

    @Builder.Default
    UserEntity.Gender gender = UserEntity.Gender.UNKNOWN;
}
