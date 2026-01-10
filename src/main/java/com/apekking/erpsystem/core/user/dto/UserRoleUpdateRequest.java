package com.apekking.erpsystem.core.user.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class UserRoleUpdateRequest {

    @NotEmpty
    public List<Long> roleIds;

    public @NotEmpty List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(@NotEmpty List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
