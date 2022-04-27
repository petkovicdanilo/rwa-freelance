package com.github.petkovicdanilo.freelance.model.api.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobsSearchOptions {
    Integer page;

    Integer pageSize;

    Double minPrice;

    Double maxPrice;

    SortByField sortBy;

    Sort.Direction sortDirection;

    Boolean isActive;

    public enum SortByField {
        price,
        title,
    }
}
