package com.example.category.Repositories;


import com.example.category.Entity.Category;
import com.example.category.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubCatRepo extends JpaRepository<SubCategory, Integer> {

    @Query("SELECT e FROM SubCategory e WHERE e.category = (:category)")
    List<SubCategory> findByCategory(Category category);

    @Query("SELECT e FROM SubCategory e WHERE e.name = (:name)")
    SubCategory findBySubCatName(String name);

    @Transactional
    List<SubCategory> deleteSubCategoryBySubId(int id);

    @Query("SELECT e FROM SubCategory e WHERE e.name LIKE %?1%")
    List<SubCategory> findByName(String name);

    @Transactional
    List<SubCategory>findSubCategoriesByCategoryCatId(int id);

    @Query("SELECT e FROM SubCategory e WHERE e.subId = (:id)")
    SubCategory findBySubId(int id);
}
