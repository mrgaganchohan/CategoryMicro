package com.example.category.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.example.category.Entity.Category;

import java.util.List;


public interface CategoryRepo extends CrudRepository<Category, Integer> {
    @Transactional
    List<Category> deleteByName(String name);

    @Query("SELECT e FROM Category e WHERE e.name LIKE %?1%")
    List<Category> findByName(String name);

    @Query("SELECT e FROM Category e WHERE e.name = (:name)")
    Category findByCatName(String name);

}