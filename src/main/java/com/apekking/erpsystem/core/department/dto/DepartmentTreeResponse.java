package com.apekking.erpsystem.core.department.dto;

import java.util.ArrayList;
import java.util.List;

public class DepartmentTreeResponse {

    private Long id;
    private String code;
    private String name;
    private Long parentId;
    private List<DepartmentTreeResponse> children = new ArrayList<>();

    public DepartmentTreeResponse(Long id, String code, String name, Long parentId) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Long getParentId() { return parentId; }
    public List<DepartmentTreeResponse> getChildren() { return children; }
}

