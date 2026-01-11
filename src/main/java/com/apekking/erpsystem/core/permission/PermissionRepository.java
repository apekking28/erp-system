package com.apekking.erpsystem.core.permission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByCode(String code);

    boolean existsByCode(String code);
}

