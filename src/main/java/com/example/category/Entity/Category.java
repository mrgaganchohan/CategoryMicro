
package com.example.category.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity // This tells Hibernate to make a table out of this class
@Table(uniqueConstraints=@UniqueConstraint(columnNames="Category_Name"))
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Category_ID")
    private int catId;

    @NotNull
    @Column(name = "Category_Name")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(){

    }

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

}
