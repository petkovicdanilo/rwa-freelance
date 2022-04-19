package com.github.petkovicdanilo.freelance.model.api.technology;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "Technology")
public class TechnologyDto {

    @Schema(description = "Technology identifier", example = "1")
    int id;

    @Schema(description = "Technology name", example = "Spring")
    String name;
}
