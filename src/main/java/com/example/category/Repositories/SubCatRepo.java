package com.example.category.Repositories;


import com.example.category.Entity.Category;
import com.example.category.Entity.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SubCatRepo extends CrudRepository<SubCategory, Integer> {

    @Query("SELECT e FROM SubCategory e WHERE e.category = (:category)")
    List<SubCategory> findByCategory(Category category);

    @Query("SELECT e FROM SubCategory e WHERE e.name = (:name)")
    SubCategory findBySubCatName(String name);

    @Transactional
    List<SubCategory> deleteByName(String name);

    @Query("SELECT e FROM SubCategory e WHERE e.name LIKE %?1%")
    List<SubCategory> findByName(String name);


    List<SubCategory>findSubCategoriesByCategoryCatId(int id);
}
