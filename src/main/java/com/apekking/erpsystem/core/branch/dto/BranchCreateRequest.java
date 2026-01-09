package com.apekking.erpsystem.core.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BranchCreateRequest {

    @NotNull
    private Long companyId;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String address;

    // getters & setters

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

