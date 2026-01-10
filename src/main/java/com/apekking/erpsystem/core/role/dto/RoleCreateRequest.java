package com.apekking.erpsystem.core.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoleCreateRequest {

    @NotNull
    public Long companyId;

    @NotBlank
    public String code;

    @NotBlank
    public String name;

    public String description;

    public @NotNull Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@NotNull Long companyId) {
        this.companyId = companyId;
    }

    public @NotBlank String getCode() {
        return code;
    }

    public void setCode(@NotBlank String code) {
        this.code = code;
    }

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

