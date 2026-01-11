package com.apekking.erpsystem.core.permission;

import com.apekking.erpsystem.core.permission.dto.PermissionCreateRequest;
import com.apekking.erpsystem.core.permission.dto.PermissionResponse;

import java.util.List;

public interface PermissionService {

    PermissionResponse create(PermissionCreateRequest request);

    PermissionResponse getById(Long id);

    List<PermissionResponse> getAll();

    void delete(Long id);
}

