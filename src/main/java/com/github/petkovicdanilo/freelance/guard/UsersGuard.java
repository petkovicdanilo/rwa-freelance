package com.github.petkovicdanilo.freelance.guard;

import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersGuard {

    private final UsersRepository usersRepository;

    public boolean checkUserId(Authentication authentication, int id) {
        String email = authentication.getName();

        Optional<UserEntity> opt = usersRepository.findById(id);

        if(!opt.isPresent()) {
            return false;
        }

        UserEntity userEntity = opt.get();
        return userEntity.getEmail().compareTo(email) == 0;
    }
}
