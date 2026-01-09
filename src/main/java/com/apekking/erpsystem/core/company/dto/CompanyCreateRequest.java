package com.apekking.erpsystem.core.company.dto;

import jakarta.validation.constraints.NotBlank;

public class CompanyCreateRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private String timezone;
    private String currency;

    // getters & setters

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

