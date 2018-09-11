
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
    private int cat_id;

    private String name;

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCat_id() {
        return cat_id;
    }

    public String getName() {
        return name;
    }
}
