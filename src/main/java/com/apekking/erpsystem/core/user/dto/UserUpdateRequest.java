package com.apekking.erpsystem.core.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class UserUpdateRequest {

    @Email
    public String email;

    public Long departmentId;

    @NotNull
    public Boolean isActive;

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public @NotNull Boolean getActive() {
        return isActive;
    }

    public void setActive(@NotNull Boolean active) {
        isActive = active;
    }
}

