package com.apekking.erpsystem.core.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}

