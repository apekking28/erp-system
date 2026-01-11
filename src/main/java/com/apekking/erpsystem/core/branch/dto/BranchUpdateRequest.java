package com.apekking.erpsystem.core.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BranchUpdateRequest {

    @NotBlank
    private String name;

    private String address;

    @NotNull
    private Boolean isActive;

    // getters & setters
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

    public @NotNull Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(@NotNull Boolean active) {
        isActive = active;
    }
}

