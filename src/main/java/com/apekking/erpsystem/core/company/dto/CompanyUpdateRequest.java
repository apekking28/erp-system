package com.apekking.erpsystem.core.company.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String status; // ACTIVE / INACTIVE

    private String timezone;
    private String currency;

    // getters & setters

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

