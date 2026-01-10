package com.apekking.erpsystem.core.permission.dto;

import com.apekking.erpsystem.core.permission.PermissionEntity;

public class PermissionResponse {

    public Long id;
    public String code;
    public String description;

    public static PermissionResponse from(PermissionEntity e) {
        PermissionResponse r = new PermissionResponse();
        r.id = e.getId();
        r.code = e.getCode();
        r.description = e.getDescription();
        return r;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

