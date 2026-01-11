package com.apekking.erpsystem.core.role;

import com.apekking.erpsystem.core.company.CompanyRepository;
import com.apekking.erpsystem.core.permission.PermissionRepository;
import com.apekking.erpsystem.core.role.dto.RoleCreateRequest;
import com.apekking.erpsystem.core.role.dto.RolePermissionUpdateRequest;
import com.apekking.erpsystem.core.role.dto.RoleResponse;
import com.apekking.erpsystem.core.role.dto.RoleUpdateRequest;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;
    private final CompanyRepository companyRepo;
    private final RolePermissionRepository rolePermissionRepo;
    private final PermissionRepository permissionRepo;

    public RoleServiceImpl(
            RoleRepository roleRepo,
            CompanyRepository companyRepo,
            RolePermissionRepository rolePermissionRepo,
            PermissionRepository permissionRepo
    ) {
        this.roleRepo = roleRepo;
        this.companyRepo = companyRepo;
        this.rolePermissionRepo = rolePermissionRepo;
        this.permissionRepo = permissionRepo;
    }


    @Override
    public RoleResponse create(RoleCreateRequest req) {

        companyRepo.findByIdAndIsDeletedFalse(req.companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));

        if (roleRepo.existsByCompanyIdAndCodeAndIsDeletedFalse(req.companyId, req.code)) {
            throw new BusinessException(ErrorCode.ROLE_CODE_EXISTS);
        }

        RoleEntity e = new RoleEntity();
        e.setCompanyId(req.companyId);
        e.setCode(req.code);
        e.setName(req.name);
        e.setDescription(req.description);

        return RoleResponse.from(roleRepo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getById(Long id) {
        return roleRepo.findByIdAndIsDeletedFalse(id)
                .map(RoleResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAll(Long companyId) {

        List<RoleEntity> list =
                roleRepo.findAllByCompanyIdAndIsDeletedFalse(companyId);

        return list.stream().map(RoleResponse::from).toList();
    }

    @Override
    public RoleResponse update(Long id, RoleUpdateRequest req) {

        RoleEntity e = roleRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));

        e.setName(req.name);
        e.setDescription(req.description);

        return RoleResponse.from(e);
    }

    @Override
    public void delete(Long id) {
        RoleEntity e = roleRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
        e.setIsDeleted(true);
    }

    @Override
    public void updatePermissions(Long roleId, RolePermissionUpdateRequest req) {

        RoleEntity role = roleRepo.findByIdAndIsDeletedFalse(roleId)
                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));

        // validate permissions exist
        for (Long permissionId : req.permissionIds) {
            permissionRepo.findById(permissionId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.PERMISSION_NOT_FOUND));
        }

        // replace permissions
        rolePermissionRepo.deleteAllByRoleId(roleId);

        for (Long permissionId : req.permissionIds) {
            RolePermissionEntity rp = new RolePermissionEntity();
            rp.setRoleId(roleId);
            rp.setPermissionId(permissionId);
            rolePermissionRepo.save(rp);
        }
    }

}

