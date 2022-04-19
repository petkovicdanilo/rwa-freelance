package com.github.petkovicdanilo.freelance.model.api.technology;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(name = "TechnologySave")
public class TechnologySaveDto {
    @Schema(description = "Technology name", example = "Spring")
    String name;
}

