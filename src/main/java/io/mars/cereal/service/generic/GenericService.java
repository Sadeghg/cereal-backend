package io.mars.cereal.service.generic;

import java.util.Collection;

public interface GenericService <T, ID>{

    T save(T t);

    Iterable<T> saveAll(Iterable<T> t);

    T findById(ID id);

    Iterable<T> findAllById(Iterable<ID> ids);

    void deleteAllById(Iterable<ID> ids);

    void delete(ID id);
}
