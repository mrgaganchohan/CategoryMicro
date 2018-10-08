package com.example.category.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.category.Entity.Category;
import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {


    @Query("SELECT e FROM Category e WHERE e.name LIKE %?1%")
    List<Category> findBySName(String name);

    @Query("SELECT e FROM Category e WHERE e.name = (:name)")
    Category findByCatName(String name);

    @Query("SELECT e.catId FROM Category e WHERE e.name = (:name)")
    int findId(String name);

    @Query("SELECT e FROM Category e WHERE e.catId = (:id)")
    Category findByCatId(int id);

    @Transactional
    List<Category> deleteByCatId(int id);



}