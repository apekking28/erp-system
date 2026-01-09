package com.apekking.erpsystem.core.department.dto;

import jakarta.validation.constraints.NotBlank;

public class DepartmentUpdateRequest {

    @NotBlank
    private String name;

    private Long parentId;

    // getters & setters

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

