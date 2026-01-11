package com.apekking.erpsystem.core.permission.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionCreateRequest {

    @NotBlank
    public String code;

    @NotBlank
    public String description;

    public @NotBlank String getCode() {
        return code;
    }

    public void setCode(@NotBlank String code) {
        this.code = code;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }
}

