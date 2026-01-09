package com.apekking.erpsystem.core.department.dto;


import com.apekking.erpsystem.core.department.DepartmentEntity;

public class DepartmentResponse {

    private Long id;
    private Long companyId;
    private Long branchId;
    private String code;
    private String name;
    private Long parentId;

    public static DepartmentResponse from(DepartmentEntity e) {
        DepartmentResponse r = new DepartmentResponse();
        r.id = e.getId();
        r.companyId = e.getCompanyId();
        r.branchId = e.getBranchId();
        r.code = e.getCode();
        r.name = e.getName();
        r.parentId = e.getParentId();
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

