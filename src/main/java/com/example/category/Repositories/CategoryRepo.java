package com.example.category.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.category.Entity.Category;




public interface CategoryRepo extends CrudRepository<Category, Integer> {
    @Transactional
    void deleteByName(String name);

}