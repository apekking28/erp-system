package com.apekking.erpsystem.core.department;

import com.apekking.erpsystem.core.branch.BranchRepository;
import com.apekking.erpsystem.core.company.CompanyRepository;
import com.apekking.erpsystem.core.department.dto.DepartmentCreateRequest;
import com.apekking.erpsystem.core.department.dto.DepartmentResponse;
import com.apekking.erpsystem.core.department.dto.DepartmentUpdateRequest;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final CompanyRepository companyRepo;
    private final BranchRepository branchRepo;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepo,
            CompanyRepository companyRepo,
            BranchRepository branchRepo
    ) {
        this.departmentRepo = departmentRepo;
        this.companyRepo = companyRepo;
        this.branchRepo = branchRepo;
    }

    @Override
    public DepartmentResponse create(DepartmentCreateRequest req) {

        // company must exist
        companyRepo.findByIdAndIsDeletedFalse(req.getCompanyId())
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));

        // branch must exist & belong to company
        branchRepo.findByIdAndIsDeletedFalse(req.getBranchId())
                .filter(b -> b.getCompanyId().equals(req.getCompanyId()))
                .orElseThrow(() -> new BusinessException(ErrorCode.BRANCH_NOT_FOUND));

        // unique code per branch
        if (departmentRepo.existsByBranchIdAndCodeAndIsDeletedFalse(
                req.getBranchId(), req.getCode())) {
            throw new BusinessException(ErrorCode.DEPARTMENT_CODE_EXISTS);
        }

        // validate parent (optional)
        if (req.getParentId() != null) {
            DepartmentEntity parent = departmentRepo
                    .findByIdAndIsDeletedFalse(req.getParentId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_PARENT_DEPARTMENT));

            boolean sameBranch =
                    parent.getBranchId().equals(req.getBranchId()) &&
                            parent.getCompanyId().equals(req.getCompanyId());

            if (!sameBranch) {
                throw new BusinessException(ErrorCode.INVALID_PARENT_DEPARTMENT);
            }
        }

        DepartmentEntity e = new DepartmentEntity();
        e.setCompanyId(req.getCompanyId());
        e.setBranchId(req.getBranchId());
        e.setCode(req.getCode());
        e.setName(req.getName());
        e.setParentId(req.getParentId());

        return DepartmentResponse.from(departmentRepo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponse getById(Long id) {
        DepartmentEntity e = departmentRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND));
        return DepartmentResponse.from(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAll(Long companyId, Long branchId) {

        List<DepartmentEntity> list;

        if (branchId != null) {
            list = departmentRepo.findAllByBranchIdAndIsDeletedFalse(branchId);
        } else if (companyId != null) {
            list = departmentRepo.findAllByCompanyIdAndIsDeletedFalse(companyId);
        } else {
            list = departmentRepo.findAllByIsDeletedFalse();
        }

        return list.stream().map(DepartmentResponse::from).toList();
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentUpdateRequest req) {

        DepartmentEntity e = departmentRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND));

        // validate parent (optional)
        if (req.getParentId() != null) {

            if (req.getParentId().equals(id)) {
                throw new BusinessException(ErrorCode.INVALID_PARENT_DEPARTMENT);
            }

            DepartmentEntity parent = departmentRepo
                    .findByIdAndIsDeletedFalse(req.getParentId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_PARENT_DEPARTMENT));

            boolean sameBranch =
                    parent.getBranchId().equals(e.getBranchId()) &&
                            parent.getCompanyId().equals(e.getCompanyId());

            if (!sameBranch) {
                throw new BusinessException(ErrorCode.INVALID_PARENT_DEPARTMENT);
            }
        }

        // immutable: companyId, branchId, code
        e.setName(req.getName());
        e.setParentId(req.getParentId());

        return DepartmentResponse.from(e); // dirty checking
    }

    @Override
    public void delete(Long id) {
        DepartmentEntity e = departmentRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.DEPARTMENT_NOT_FOUND));
        e.setIsDeleted(true); // soft delete
    }
}

