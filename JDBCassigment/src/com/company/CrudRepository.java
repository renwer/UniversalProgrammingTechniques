package com.company;

import java.util.List;

//created this instead of IUserRepository and IGroupRepository to avoid unnecessary code duplication

public interface CrudRepository<Type, IdClass> {
    String findById(IdClass id);
    List<String> findByName(String name);
    void add(Type element);
    void update(Type element);
    void delete(Type element);
}
