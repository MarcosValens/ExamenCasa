package com.marcosvalens.dao;

import java.util.List;

public interface DAO <T>{

    default T getUser(String userName) throws Exception{
        throw new Exception();
    }

    default T getSchool(long id) throws Exception{
        throw new Exception();
    }

    List<T> getAll();

    void save(T t);

    void update(T t,T u);

    void delete(T t);
}
