package com.github.petkovicdanilo.freelance.model.api.user;

import com.github.petkovicdanilo.freelance.model.common.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersSearchOptions {

    Integer page;

    Integer pageSize;

    SortByField sortBy;

    Sort.Direction sortDirection;

    String firstName;

    String lastName;

    Gender gender;

    public enum SortByField {
        firstName,
        lastName
    }
}
