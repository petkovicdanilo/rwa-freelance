package com.github.petkovicdanilo.freelance.model.api.technology;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Jacksonized @Builder
@Schema(name = "TechnologySave")
public class TechnologySaveDto {
    @Schema(description = "Technology name", example = "Spring")
    @NotBlank
    @Size(max = 32)
    String name;
}

