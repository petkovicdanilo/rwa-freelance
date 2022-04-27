package com.github.petkovicdanilo.freelance.service;

import com.github.petkovicdanilo.freelance.model.api.ErrorInfo;
import com.github.petkovicdanilo.freelance.exception.ResourceNotFoundException;
import com.github.petkovicdanilo.freelance.exception.UniqueViolationException;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.model.mapper.UsersMapper;
import com.github.petkovicdanilo.freelance.repository.TechnologiesRepository;
import com.github.petkovicdanilo.freelance.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    private final TechnologiesRepository technologiesRepository;

    public List<UserDto> getAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getOne(int id) throws ResourceNotFoundException {
        UserEntity userEntity = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        return usersMapper.toDto(userEntity);
    }

    public UserDto save(UserSaveDto user) throws UniqueViolationException {
        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(user.getTechnologyIds());

        if(usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        UserEntity userEntity = usersMapper.toEntity(user);
        userEntity.setTechnologies(technologies);
        usersRepository.save(userEntity);

        return usersMapper.toDto(userEntity);
    }

    public UserDto update(int id, UserSaveDto updatedUser) throws ResourceNotFoundException, UniqueViolationException {
        Set<TechnologyEntity> technologies = technologiesRepository.findAllById(updatedUser.getTechnologyIds());

        UserEntity user = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorInfo.ResourceType.USER));

        if(!user.getEmail().equals(updatedUser.getEmail())
                && usersRepository.findByEmail(updatedUser.getEmail()).isPresent()) {
            throw new UniqueViolationException(ErrorInfo.ResourceType.USER, "'email' already exists");
        }

        UserEntity updatedUserEntity = usersMapper.toEntity(updatedUser);
        updatedUserEntity.setId(id);
        updatedUserEntity.setTechnologies(technologies);

        updatedUserEntity.setApplications(user.getApplications());
        updatedUserEntity.setPostedJobs(user.getPostedJobs());

        usersRepository.save(updatedUserEntity);

        return usersMapper.toDto(updatedUserEntity);
    }

    public void remove(int id) throws ResourceNotFoundException {
        if(!usersRepository.existsById(id)) {
            throw new ResourceNotFoundException(ErrorInfo.ResourceType.USER);
        }

        usersRepository.deleteById(id);
    }
}
