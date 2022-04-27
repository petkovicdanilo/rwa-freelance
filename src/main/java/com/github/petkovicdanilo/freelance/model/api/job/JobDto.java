package com.github.petkovicdanilo.freelance.model.api.job;

import com.github.petkovicdanilo.freelance.model.api.technology.TechnologyDto;
import com.github.petkovicdanilo.freelance.model.api.user.UserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@Schema(name = "Job")
public class JobDto {
    @Schema(description = "Job identifier", example = "1")
    int id;

    @Schema(description = "Job title", example = "Spring + React app")
    String title;

    @Schema(description = "Job description",
            example = "Create a full stack web app using Spring on backend and React on frontend")
    String description;

    @Schema(description = "Job price", example = "500.0")
    double price;

    @Schema(description = "Is job still open for applications")
    boolean active;

    @Schema(description = "Id of the user who posted this job")
    int employerId;

    @Schema(description = "Technologies associated with job")
    List<TechnologyDto> technologies;

}
