package com.apekking.erpsystem.core.role.dto;

import com.apekking.erpsystem.core.role.RoleEntity;

public class RoleResponse {

    public Long id;
    public Long companyId;
    public String code;
    public String name;
    public String description;

    public static RoleResponse from(RoleEntity e) {
        RoleResponse r = new RoleResponse();
        r.id = e.getId();
        r.companyId = e.getCompanyId();
        r.code = e.getCode();
        r.name = e.getName();
        r.description = e.getDescription();
        return r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

