package com.solvd.laba.block2.bankhierarchy.persistance;

public interface crudOperations<T> {
    public void create(T data);
    public T getById(Long id);
    public Long updateById(Long id);
    public Long deleteById(Long id);
}
