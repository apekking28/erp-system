package com.apekking.erpsystem.core.role;

import com.apekking.erpsystem.core.role.dto.RoleCreateRequest;
import com.apekking.erpsystem.core.role.dto.RolePermissionUpdateRequest;
import com.apekking.erpsystem.core.role.dto.RoleResponse;
import com.apekking.erpsystem.core.role.dto.RoleUpdateRequest;

import java.util.List;

public interface RoleService {

    RoleResponse create(RoleCreateRequest request);

    RoleResponse getById(Long id);

    List<RoleResponse> getAll(Long companyId);

    RoleResponse update(Long id, RoleUpdateRequest request);

    void delete(Long id);

    void updatePermissions(Long roleId, RolePermissionUpdateRequest request);
}

