package com.github.petkovicdanilo.freelance.model.api.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Jacksonized @Builder
@Schema(name = "ApplicationSave")
public class ApplicationSaveDto {

    @Schema(
        description = "Text of the application where user explains why he is good fit for job",
        example = "I have the right experience for this job")
    @NotBlank
    @Size(min = 10, max = 1024)
    String text;
}