package com.apekking.erpsystem.core.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository
        extends JpaRepository<RolePermissionEntity, Long> {

    List<RolePermissionEntity> findAllByRoleId(Long roleId);

    void deleteAllByRoleId(Long roleId);

    List<RolePermissionEntity> findAllByRoleIdIn(List<Long> roleIds);
}

