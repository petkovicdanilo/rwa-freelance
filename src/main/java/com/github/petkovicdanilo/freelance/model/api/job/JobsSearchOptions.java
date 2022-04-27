package com.github.petkovicdanilo.freelance.model.api.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobsSearchOptions {
    Integer page;

    Integer pageSize;

    Double minPrice;
}
