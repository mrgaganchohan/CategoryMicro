
package com.example.category.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity // This tells Hibernate to make a table out of this class
@Table(uniqueConstraints=@UniqueConstraint(columnNames="NAME"))
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int catId;
    @NotEmpty
    private String name;

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
