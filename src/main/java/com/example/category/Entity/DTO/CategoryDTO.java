package com.example.category.Entity.DTO;


import javax.validation.constraints.NotNull;

public class CategoryDTO {
    private int catId;
    @NotNull
    private String name;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
