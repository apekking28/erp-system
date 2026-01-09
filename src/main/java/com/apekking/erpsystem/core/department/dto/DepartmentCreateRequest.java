package com.apekking.erpsystem.core.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepartmentCreateRequest {

    @NotNull
    private Long companyId;

    @NotNull
    private Long branchId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private Long parentId;

    // getters & setters

    public @NotNull Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(@NotNull Long companyId) {
        this.companyId = companyId;
    }

    public @NotNull Long getBranchId() {
        return branchId;
    }

    public void setBranchId(@NotNull Long branchId) {
        this.branchId = branchId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

