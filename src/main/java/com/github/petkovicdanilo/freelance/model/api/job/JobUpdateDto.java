package com.github.petkovicdanilo.freelance.model.api.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Value
@Builder
@Schema(name = "JobUpdate")
public class JobUpdateDto {

    @Schema(description = "Job title", example = "Spring + React app")
    @NotBlank
    @Size(max = 64)
    String title;

    @Schema(description = "Job description",
            example = "Create a full stack web app using Spring on backend and React on frontend")
    @NotBlank
    @Size(max = 1024)
    String description;

    @Schema(description = "Job price", example = "500.0")
    double price;

    @Schema(description = "Ids of technologies associated with job")
    @NotEmpty
    List<Integer> technologyIds;
}
