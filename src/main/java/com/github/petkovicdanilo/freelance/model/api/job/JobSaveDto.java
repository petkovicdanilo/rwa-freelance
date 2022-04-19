package com.github.petkovicdanilo.freelance.model.api.job;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JobSaveDto {

    String title;

    String description;

    double price;

}
