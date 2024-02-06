package com.solvd.laba.block2.bankhierarchy.persistance;

import com.solvd.laba.block2.bankhierarchy.ConnectionPool;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrudOperations<T> {
    public final static ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    public Long create(T data);

    public T getById(Long id);

    public List<T> getAll();

    public Long updateById(@Param("id") Long id, @Param("data") T data);

    public Long update(T data);

    public Long deleteById(Long id);
}
