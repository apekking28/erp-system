package com.apekking.erpsystem.security.auth;

import com.apekking.erpsystem.core.user.*;
import com.apekking.erpsystem.core.role.*;
import com.apekking.erpsystem.core.permission.*;
import com.apekking.erpsystem.exception.*;
import com.apekking.erpsystem.security.auth.dto.*;
import com.apekking.erpsystem.security.jwt.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final UserRoleRepository userRoleRepo;
    private final RoleRepository roleRepo;
    private final RolePermissionRepository rolePermRepo;
    private final PermissionRepository permRepo;
    private final JwtTokenProvider provider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(
            UserRepository userRepo,
            UserRoleRepository userRoleRepo,
            RoleRepository roleRepo,
            RolePermissionRepository rolePermRepo,
            PermissionRepository permRepo,
            JwtTokenProvider provider
    ) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.roleRepo = roleRepo;
        this.rolePermRepo = rolePermRepo;
        this.permRepo = permRepo;
        this.provider = provider;
    }

    @Override
    public LoginResponse login(LoginRequest req) {

        UserEntity user = userRepo
                .findByUsernameAndIsDeletedFalse(req.getUsername())
                .or(() -> userRepo.findByEmailAndIsDeletedFalse(req.getEmail()))
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }
        if (!user.getIsActive()) {
            throw new BusinessException(ErrorCode.USER_INACTIVE);
        }

        var roleIds = userRoleRepo.findAllByUserId(user.getId())
                .stream().map(UserRoleEntity::getRoleId).toList();

        List<String> roles = roleRepo.findAllById(roleIds)
                .stream().map(RoleEntity::getCode).toList();

        List<Long> permIds = rolePermRepo.findAllByRoleIdIn(roleIds)
                .stream().map(RolePermissionEntity::getPermissionId).toList();

        List<String> permissions = permRepo.findAllById(permIds)
                .stream().map(PermissionEntity::getCode).toList();

        JwtClaims claims = new JwtClaims();
        claims.userId = user.getId();
        claims.companyId = user.getCompanyId();
        claims.branchId = user.getBranchId();
        claims.roles = roles;
        claims.permissions = permissions;

        LoginResponse res = new LoginResponse();
        res.accessToken = provider.generate(claims);
        res.expiresIn = 900;
        return res;
    }
}
