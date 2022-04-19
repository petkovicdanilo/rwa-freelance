package com.github.petkovicdanilo.freelance.model.api.technology;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TechnologyDto {
    int id;
    String name;
}
