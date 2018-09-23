package com.example.category.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="Sub_Category_Name"))
public class SubCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Sub_Category_ID")
    private int subId;

    @NotNull
    @Column(name = "Sub_Category_Name")
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Category_ID", referencedColumnName = "Category_ID")
    private Category category;

    public SubCategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public SubCategory(){
    }


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
}
