package com.nicasia.rfc.shared.abstracts;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public abstract class BaseRepositoryImpl<T extends BaseEntity, R extends BaseRepository<T>> extends
        QuerydslRepositorySupport implements BaseRepositoryCustom<T>{

    protected R repository;


    public BaseRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }
}
