package io.mars.cereal.data;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.stream.Stream;

public class GenericSpecification {

    public static <T> Specification<T> and(Specification<T>... specs) {
        return (root, query, builder) -> {
            Predicate[] predicates = Stream.of(specs)
                    .map(spec -> spec.toPredicate(root, query, builder))
                    .toArray(Predicate[]::new);
            return builder.and(predicates);
        };
    }

    public static <T> Specification<T> or(Specification<T>... specs) {
        return (root, query, builder) -> {
            Predicate[] predicates = Arrays.stream(specs)
                    .map(spec -> spec.toPredicate(root, query, builder))
                    .toArray(Predicate[]::new);
            return builder.or(predicates);
        };
    }
}
