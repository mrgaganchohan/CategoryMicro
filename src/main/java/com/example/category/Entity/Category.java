
package com.example.category.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
@Table
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Category_ID")
    private int catId;

    @NotNull
    @Column(name = "Category_Name", unique = true)
    private String name;

    @NotNull
    @Column(name = "Category_Status")
    private String status;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   //@JsonIgnore
    private List<SubCategory> subcategory;


    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getCatId() {
        return catId;
    }

    public String getName() {
        return name;
    }

    public void setSubcategory(List<SubCategory> subcategory) {
        this.subcategory = subcategory;
    }

    public List<SubCategory> getSubcategories() {
        return subcategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
