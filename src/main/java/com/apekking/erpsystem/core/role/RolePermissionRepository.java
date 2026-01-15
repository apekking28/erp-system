package com.apekking.erpsystem.core.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RolePermissionRepository
        extends JpaRepository<RolePermissionEntity, Long> {

    List<RolePermissionEntity> findAllByRoleId(Long roleId);

    void deleteAllByRoleId(Long roleId);

    List<RolePermissionEntity> findAllByRoleIdIn(List<Long> roleIds);

    @Query("select rp.permissionId from RolePermissionEntity rp where rp.roleId = :roleId")
    Set<Long> findPermissionIdsByRoleId(@Param("roleId") Long roleId);

}

