package com.example.category.Entity.DTO;

import javax.validation.constraints.NotNull;

public class SubCategoryDTO {

    private int subId;
    @NotNull
    private String name;
    @NotNull
    private String status;
    @NotNull
    private CategoryDTO categoryDTO;

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
