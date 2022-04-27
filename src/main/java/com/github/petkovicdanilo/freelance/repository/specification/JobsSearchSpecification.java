package com.github.petkovicdanilo.freelance.repository.specification;

import com.github.petkovicdanilo.freelance.model.api.job.JobsSearchOptions;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity;
import com.github.petkovicdanilo.freelance.model.entity.JobEntity_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class JobsSearchSpecification implements Specification<JobEntity> {

    private final JobsSearchOptions jobsSearchOptions;

    @Override
    public Predicate toPredicate(Root<JobEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        root.fetch(JobEntity_.technologies);
        root.fetch(JobEntity_.employer);

        Path<Double> price = root.get(JobEntity_.price);
        Path<String> title = root.get(JobEntity_.title);
        Path<Boolean> active = root.get(JobEntity_.active);

        if(jobsSearchOptions.getMinPrice() != null) {
            predicates.add(criteriaBuilder.ge(price, jobsSearchOptions.getMinPrice()));
        }

        if(jobsSearchOptions.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.le(price, jobsSearchOptions.getMaxPrice()));
        }

        if(jobsSearchOptions.getIsActive() != null) {
            predicates.add(criteriaBuilder.equal(active, jobsSearchOptions.getIsActive()));
        }

        JobsSearchOptions.SortByField sortBy = jobsSearchOptions.getSortBy();
        if(sortBy != null) {
            Path<?> propertyToSortBy = null;

            switch (sortBy) {
                case price:
                    propertyToSortBy = price;
                    break;
                case title:
                    propertyToSortBy = title;
                    break;
            }

            Sort.Direction direction = jobsSearchOptions.getSortDirection();
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
