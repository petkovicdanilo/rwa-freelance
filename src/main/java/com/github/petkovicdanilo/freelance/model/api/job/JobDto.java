package com.github.petkovicdanilo.freelance.model.api.job;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "Job")
public class JobDto {
    @Schema(description = "Job identifier", example = "1")
    int id;

    String title;

    String description;

    double price;

}
