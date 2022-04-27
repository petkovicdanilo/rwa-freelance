package com.github.petkovicdanilo.freelance.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersSearchOptions {

    Integer page;

    Integer pageSize;
}
