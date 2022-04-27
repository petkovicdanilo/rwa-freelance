package com.github.petkovicdanilo.freelance.model.mapper;

import com.github.petkovicdanilo.freelance.model.api.application.ApplicationDto;
import com.github.petkovicdanilo.freelance.model.api.application.ApplicationSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.ApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApplicationsMapper {

    @Mappings({
        @Mapping(source = "candidate.id", target = "candidateId"),
        @Mapping(source = "job.id", target = "jobId")
    })
    ApplicationDto toDto(ApplicationEntity entity);

    ApplicationEntity toEntity(ApplicationSaveDto applicationSaveDto);
}
