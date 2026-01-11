package com.apekking.erpsystem.core.user;

import com.apekking.erpsystem.core.user.dto.UserCreateRequest;
import com.apekking.erpsystem.core.user.dto.UserResponse;
import com.apekking.erpsystem.core.user.dto.UserRoleUpdateRequest;
import com.apekking.erpsystem.core.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse create(UserCreateRequest request);

    UserResponse getById(Long id);

    List<UserResponse> getAll(Long companyId, Long branchId, Long departmentId);

    UserResponse update(Long id, UserUpdateRequest request);

    void delete(Long id);

    void updateRoles(Long userId, UserRoleUpdateRequest request);
}

