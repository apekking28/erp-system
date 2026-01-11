package com.apekking.erpsystem.core.department;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    boolean existsByBranchIdAndCodeAndIsDeletedFalse(Long branchId, String code);

    Optional<DepartmentEntity> findByIdAndIsDeletedFalse(Long id);

    List<DepartmentEntity> findAllByIsDeletedFalse();

    List<DepartmentEntity> findAllByCompanyIdAndIsDeletedFalse(Long companyId);

    List<DepartmentEntity> findAllByBranchIdAndIsDeletedFalse(Long branchId);
}

