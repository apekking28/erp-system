package com.apekking.erpsystem.core.role.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleUpdateRequest {

    @NotBlank
    public String name;

    public String description;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

