package com.apekking.erpsystem.core.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByIdAndIsDeletedFalse(Long id);

    boolean existsByCompanyIdAndCodeAndIsDeletedFalse(Long companyId, String code);

    List<RoleEntity> findAllByCompanyIdAndIsDeletedFalse(Long companyId);
}

