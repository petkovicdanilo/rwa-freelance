package com.github.petkovicdanilo.freelance.model.mapper;

import com.github.petkovicdanilo.freelance.model.FreelanceUserDetails;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.model.mapper.util.AuthoritiesMapper;
import com.github.petkovicdanilo.freelance.model.mapper.util.PasswordEncoderMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {AuthoritiesMapper.class, PasswordEncoderMapper.class })
public interface UsersMapper {

    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "password", qualifiedByName = "encodePassword")
    UserEntity toEntity(UserSaveDto userSaveDto);

    @Mappings({
        @Mapping(source = "email", target = "username"),
        @Mapping(source = "admin", target = "authorities", qualifiedByName = "mapAuthorities")
    })
    FreelanceUserDetails toUserDetails(UserEntity userEntity);

}
