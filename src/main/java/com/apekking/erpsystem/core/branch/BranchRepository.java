package com.apekking.erpsystem.core.branch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    boolean existsByCompanyIdAndCodeAndIsDeletedFalse(Long companyId, String code);

    Optional<BranchEntity> findByIdAndIsDeletedFalse(Long id);

    List<BranchEntity> findAllByIsDeletedFalse();

    List<BranchEntity> findAllByCompanyIdAndIsDeletedFalse(Long companyId);
}

