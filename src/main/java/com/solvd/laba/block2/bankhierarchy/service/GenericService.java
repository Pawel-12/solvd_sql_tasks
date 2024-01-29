package com.solvd.laba.block2.bankhierarchy.service;

import java.util.List;

public interface GenericService<T> {
    public Long create(T data);

    public T getById(Long id);

    public List<T> getAll();

    public Long updateById(Long id, T data);

    public Long update(T data);

    public Long deleteById(Long id);
}
