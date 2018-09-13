
package com.example.category.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity // This tells Hibernate to make a table out of this class
@Table(uniqueConstraints=@UniqueConstraint(columnNames="NAME"))
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int catId;

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
