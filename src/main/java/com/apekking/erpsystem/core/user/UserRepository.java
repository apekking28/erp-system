package com.apekking.erpsystem.core.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdAndIsDeletedFalse(Long id);

    Optional<UserEntity> findByUsernameAndIsDeletedFalse(String username);

    Optional<UserEntity> findByEmailAndIsDeletedFalse(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<UserEntity> findAllByIsDeletedFalse();

    List<UserEntity> findAllByCompanyIdAndIsDeletedFalse(Long companyId);

    List<UserEntity> findAllByBranchIdAndIsDeletedFalse(Long branchId);

    List<UserEntity> findAllByDepartmentIdAndIsDeletedFalse(Long departmentId);

    Optional<UserEntity> findByUsernameOrEmailAndIsDeletedFalse(String username, String email);

}

