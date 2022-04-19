package com.github.petkovicdanilo.freelance.model.mapper;

import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.technology.TechnologySaveDto;
import com.github.petkovicdanilo.freelance.model.entity.TechnologyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TechnologiesMapper {
    TechnologyDto toDto(TechnologyEntity technologyEntity);
    TechnologyEntity toEntity(TechnologySaveDto technologySaveDto);
}
