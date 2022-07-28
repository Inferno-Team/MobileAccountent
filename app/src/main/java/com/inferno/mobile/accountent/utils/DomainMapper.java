package com.inferno.mobile.accountent.utils;

public interface DomainMapper<T, DomainModel> {
    DomainModel mapToDomain(T model);

    T mapFromDomain(DomainModel domainModel);
}
