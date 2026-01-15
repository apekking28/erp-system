package com.apekking.erpsystem.core.user;

import com.apekking.erpsystem.core.branch.BranchRepository;
import com.apekking.erpsystem.core.company.CompanyRepository;
import com.apekking.erpsystem.core.department.DepartmentRepository;
import com.apekking.erpsystem.core.role.RoleRepository;
import com.apekking.erpsystem.core.user.dto.UserCreateRequest;
import com.apekking.erpsystem.core.user.dto.UserResponse;
import com.apekking.erpsystem.core.user.dto.UserRoleUpdateRequest;
import com.apekking.erpsystem.core.user.dto.UserUpdateRequest;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final CompanyRepository companyRepo;
    private final BranchRepository branchRepo;
    private final DepartmentRepository departmentRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRoleRepository userRoleRepo;
    private final RoleRepository roleRepo;

    public UserServiceImpl(
            UserRepository userRepo,
            CompanyRepository companyRepo,
            BranchRepository branchRepo,
            DepartmentRepository departmentRepo,
            RoleRepository roleRepo,
            UserRoleRepository userRoleRepo
    ) {
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.branchRepo = branchRepo;
        this.departmentRepo = departmentRepo;
        this.roleRepo = roleRepo;
        this.userRoleRepo = userRoleRepo;
    }


    @Override
    public UserResponse create(UserCreateRequest req) {

        companyRepo.findByIdAndIsDeletedFalse(req.companyId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));

        branchRepo.findByIdAndIsDeletedFalse(req.branchId)
                .orElseThrow(() -> new BusinessException(ErrorCode.BRANCH_NOT_FOUND));

        if (req.departmentId != null) {
            departmentRepo.findByIdAndIsDeletedFalse(req.departmentId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND));
        }

        if (userRepo.existsByUsername(req.username)) {
            throw new BusinessException(ErrorCode.USERNAME_EXISTS);
        }

        if (userRepo.existsByEmail(req.email)) {
            throw new BusinessException(ErrorCode.EMAIL_EXISTS);
        }

        UserEntity e = new UserEntity();
        e.setCompanyId(req.companyId);
        e.setBranchId(req.branchId);
        e.setDepartmentId(req.departmentId);
        e.setUsername(req.username);
        e.setEmail(req.email);
        e.setPassword(encoder.encode(req.password));
        e.setIsActive(true);

        return UserResponse.from(userRepo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        return userRepo.findByIdAndIsDeletedFalse(id)
                .map(UserResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll(Long companyId, Long branchId, Long departmentId) {

        List<UserEntity> list;

        if (departmentId != null) {
            list = userRepo.findAllByDepartmentIdAndIsDeletedFalse(departmentId);
        } else if (branchId != null) {
            list = userRepo.findAllByBranchIdAndIsDeletedFalse(branchId);
        } else if (companyId != null) {
            list = userRepo.findAllByCompanyIdAndIsDeletedFalse(companyId);
        } else {
            list = userRepo.findAllByIsDeletedFalse();
        }

        return list.stream().map(UserResponse::from).toList();
    }

    @Override
    public UserResponse update(Long id, UserUpdateRequest req) {

        UserEntity e = userRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (req.email != null && !req.email.equals(e.getEmail())) {
            if (userRepo.existsByEmail(req.email)) {
                throw new BusinessException(ErrorCode.EMAIL_EXISTS);
            }
            e.setEmail(req.email);
        }

        e.setDepartmentId(req.departmentId);
        e.setIsActive(req.isActive);

        return UserResponse.from(e); // dirty checking
    }

    @Override
    public void delete(Long id) {
        UserEntity e = userRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        e.setIsDeleted(true);
    }

    @Override
    public void updateRoles(Long userId, UserRoleUpdateRequest req) {

        UserEntity user = userRepo.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // validate role & company scope
        for (Long roleId : req.roleIds) {
            roleRepo.findByIdAndIsDeletedFalse(roleId)
                    .filter(r -> r.getCompanyId().equals(user.getCompanyId()))
                    .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
        }

        Set<Long> existingRoleIds =
                userRoleRepo.findRoleIdsByUserId(userId);

        for (Long roleId : req.roleIds) {
            if (!existingRoleIds.contains(roleId)) {
                UserRoleEntity ur = new UserRoleEntity();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                userRoleRepo.save(ur);
            }
        }
    }

}

