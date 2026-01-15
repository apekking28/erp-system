package com.apekking.erpsystem.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);

    @Query("select ur.roleId from UserRoleEntity ur where ur.userId = :userId")
    Set<Long> findRoleIdsByUserId(@Param("userId") Long userId);

}

