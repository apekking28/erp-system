package com.apekking.erpsystem.core.user.dto;

import com.apekking.erpsystem.core.user.UserEntity;

public class UserResponse {

    public Long id;
    public Long companyId;
    public Long branchId;
    public Long departmentId;
    public String username;
    public String email;
    public Boolean isActive;

    public static UserResponse from(UserEntity e) {
        UserResponse r = new UserResponse();
        r.id = e.getId();
        r.companyId = e.getCompanyId();
        r.branchId = e.getBranchId();
        r.departmentId = e.getDepartmentId();
        r.username = e.getUsername();
        r.email = e.getEmail();
        r.isActive = e.getIsActive();
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}

