package com.example.category.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class SubCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Sub_Category_ID")
    private int subId;

    @NotNull
    @Column(name = "Sub_Category_Name", unique = true)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name="Category_ID", nullable =false)
    private Category category;

    @NotNull
    @Column(name = "Category_Status")
    private String status;


    public int getSubId() {
        return subId;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
