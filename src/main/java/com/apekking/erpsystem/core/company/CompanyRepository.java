package com.apekking.erpsystem.core.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByCodeAndIsDeletedFalse(String code);

    Optional<CompanyEntity> findByIdAndIsDeletedFalse(Long id);
}

