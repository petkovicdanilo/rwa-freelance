package com.github.petkovicdanilo.freelance.repository.specification;

import com.github.petkovicdanilo.freelance.model.api.user.UsersSearchOptions;
import com.github.petkovicdanilo.freelance.model.common.Gender;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity;
import com.github.petkovicdanilo.freelance.model.entity.UserEntity_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UsersSearchSpecification implements Specification<UserEntity> {

    private final UsersSearchOptions usersSearchOptions;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        root.fetch(UserEntity_.technologies);

        Path<String> firstName = root.get(UserEntity_.firstName);
        Path<String> lastName = root.get(UserEntity_.lastName);
        Path<Gender> gender = root.get(UserEntity_.gender);

        if(usersSearchOptions.getFirstName() != null) {
            predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(firstName),
                "%" + usersSearchOptions.getFirstName().toLowerCase() + "%"
            ));
        }

        if(usersSearchOptions.getLastName() != null) {
            predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(lastName),
                "%" + usersSearchOptions.getLastName() + "%"
            ));
        }

        if(usersSearchOptions.getGender() != null) {
            predicates.add(criteriaBuilder.equal(gender, usersSearchOptions.getGender()));
        }

        UsersSearchOptions.SortByField sortBy = usersSearchOptions.getSortBy();
        if(sortBy != null) {
            Path<?> propertyToSortBy = null;

            switch (sortBy) {
                case firstName:
                    propertyToSortBy = firstName;
                    break;
                case lastName:
                    propertyToSortBy = lastName;
                    break;
            }

            Sort.Direction direction = usersSearchOptions.getSortDirection();
            if(direction == null || direction == Sort.Direction.ASC) {
                query.orderBy(criteriaBuilder.asc(propertyToSortBy));
            }
            else {
                query.orderBy(criteriaBuilder.desc(propertyToSortBy));
            }
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
