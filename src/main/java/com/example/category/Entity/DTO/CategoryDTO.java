package com.example.category.Entity.DTO;


import javax.validation.constraints.NotNull;

public class CategoryDTO {
    private int catId;
    @NotNull
    private String name;

    @NotNull
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
