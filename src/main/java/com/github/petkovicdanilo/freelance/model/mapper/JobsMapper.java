package com.github.petkovicdanilo.freelance.model.mapper;

import com.github.petkovicdanilo.freelance.model.api.job.JobDto;
import com.github.petkovicdanilo.freelance.model.api.job.JobSaveDto;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobsMapper {

    JobDto toDto(JobEntity jobEntity);

    JobEntity toEntity(JobSaveDto jobDto);
}
