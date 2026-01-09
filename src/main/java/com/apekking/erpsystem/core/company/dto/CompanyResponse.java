package com.apekking.erpsystem.core.company.dto;

import com.apekking.erpsystem.core.company.CompanyEntity;

public class CompanyResponse {

    private Long id;
    private String code;
    private String name;
    private String status;
    private String timezone;
    private String currency;

    public static CompanyResponse from(CompanyEntity e) {
        CompanyResponse r = new CompanyResponse();
        r.id = e.getId();
        r.code = e.getCode();
        r.name = e.getName();
        r.status = e.getStatus();
        r.timezone = e.getTimezone();
        r.currency = e.getCurrency();
        return r;
    }

    // getters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

