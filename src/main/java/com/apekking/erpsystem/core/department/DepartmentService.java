package com.apekking.erpsystem.core.department;

import com.apekking.erpsystem.core.department.dto.DepartmentCreateRequest;
import com.apekking.erpsystem.core.department.dto.DepartmentResponse;
import com.apekking.erpsystem.core.department.dto.DepartmentUpdateRequest;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse create(DepartmentCreateRequest request);

    DepartmentResponse getById(Long id);

    List<DepartmentResponse> getAll(Long companyId, Long branchId);

    DepartmentResponse update(Long id, DepartmentUpdateRequest request);

    void delete(Long id);
}

