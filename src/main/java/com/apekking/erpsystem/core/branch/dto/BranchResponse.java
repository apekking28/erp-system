package com.apekking.erpsystem.core.branch.dto;

import com.apekking.erpsystem.core.branch.BranchEntity;

public class BranchResponse {

    private Long id;
    private Long companyId;
    private String code;
    private String name;
    private String address;
    private Boolean isActive;

    public static BranchResponse from(BranchEntity e) {
        BranchResponse r = new BranchResponse();
        r.id = e.getId();
        r.companyId = e.getCompanyId();
        r.code = e.getCode();
        r.name = e.getName();
        r.address = e.getAddress();
        r.isActive = e.getIsActive();
        return r;
    }

    // getters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

