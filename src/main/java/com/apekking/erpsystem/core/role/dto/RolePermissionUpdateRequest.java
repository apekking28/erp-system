package com.apekking.erpsystem.core.role.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class RolePermissionUpdateRequest {

    @NotEmpty
    public List<Long> permissionIds;

    public @NotEmpty List<Long> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(@NotEmpty List<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
}

