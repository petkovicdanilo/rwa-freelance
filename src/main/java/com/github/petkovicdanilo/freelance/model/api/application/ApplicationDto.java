package com.github.petkovicdanilo.freelance.model.api.application;

import com.github.petkovicdanilo.freelance.model.common.ApplicationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
@Schema(name = "Application")
public class ApplicationDto {

    @Schema(
        description = "Text of the application where user explains why he is good fit for job",
        example = "I have the right experience for this job")
    String text;

    @Schema(description = "Date and time of creation")
    Date createdAt;

    @Schema(description = "Date and time of last update")
    Date updatedAt;

    @Schema(description = "Id of the user who created this application", example = "1")
    int candidateId;

    @Schema(description = "Id of the job", example = "1")
    int jobId;

    @Schema(description = "Status of the application", example = "PENDING")
    ApplicationStatus status;
}