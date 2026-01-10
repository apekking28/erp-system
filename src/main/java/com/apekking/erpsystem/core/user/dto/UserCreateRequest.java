package com.apekking.erpsystem.core.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreateRequest {

    @NotNull
    public Long companyId;

    @NotNull
    public Long branchId;

    public Long departmentId;

    @NotBlank
    public String username;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String password;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }
}

