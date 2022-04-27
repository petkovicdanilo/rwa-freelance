package com.github.petkovicdanilo.freelance.model.api.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Schema(name = "JobSave")
public class JobSaveDto {

    @Schema(description = "Job title", example = "Spring + React app")
    String title;

    @Schema(description = "Job description",
            example = "Create a full stack web app using Spring on backend and React on frontend")
    String description;

    @Schema(description = "Job price", example = "500.0")
    double price;

    @Schema(description = "Ids of technologies associated with job")
    List<Integer> technologyIds;
}
