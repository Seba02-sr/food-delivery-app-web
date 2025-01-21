package com.mycompany.tpdsw.mapper;

public interface Mapper<T, D> {
    D mapToDto(T entity);

    T mapToEntity(D dto);
}
