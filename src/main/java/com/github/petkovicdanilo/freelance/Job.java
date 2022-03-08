package com.github.petkovicdanilo.freelance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Job {
    private final int id;
    private final String description;
}
