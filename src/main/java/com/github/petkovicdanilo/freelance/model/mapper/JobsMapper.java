package com.github.petkovicdanilo.freelance.model.mapper;

import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobsMapper {

    @Mapping(source = "employer.id", target = "employerId")
    JobDto toDto(JobEntity jobEntity);

    JobEntity toEntity(JobSaveDto jobDto);
}
