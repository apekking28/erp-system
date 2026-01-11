package com.apekking.erpsystem.core.branch;

import com.apekking.erpsystem.core.branch.dto.BranchCreateRequest;
import com.apekking.erpsystem.core.branch.dto.BranchResponse;
import com.apekking.erpsystem.core.branch.dto.BranchUpdateRequest;
import com.apekking.erpsystem.core.company.CompanyRepository;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepo;
    private final CompanyRepository companyRepo;

    public BranchServiceImpl(BranchRepository branchRepo, CompanyRepository companyRepo) {
        this.branchRepo = branchRepo;
        this.companyRepo = companyRepo;
    }

    @Override
    public BranchResponse create(BranchCreateRequest req) {

        // company must exist
        companyRepo.findByIdAndIsDeletedFalse(req.getCompanyId())
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));

        // unique code per company
        if (branchRepo.existsByCompanyIdAndCodeAndIsDeletedFalse(req.getCompanyId(), req.getCode())) {
            throw new BusinessException(ErrorCode.BRANCH_CODE_EXISTS);
        }

        BranchEntity e = new BranchEntity();
        e.setCompanyId(req.getCompanyId());
        e.setCode(req.getCode());
        e.setName(req.getName());
        e.setAddress(req.getAddress());
        e.setIsActive(true);

        return BranchResponse.from(branchRepo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public BranchResponse getById(Long id) {
        BranchEntity e = branchRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.BRANCH_NOT_FOUND));
        return BranchResponse.from(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BranchResponse> getAll(Long companyId) {
        List<BranchEntity> list =
                (companyId == null)
                        ? branchRepo.findAllByIsDeletedFalse()
                        : branchRepo.findAllByCompanyIdAndIsDeletedFalse(companyId);

        return list.stream().map(BranchResponse::from).toList();
    }

    @Override
    public BranchResponse update(Long id, BranchUpdateRequest req) {
        BranchEntity e = branchRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.BRANCH_NOT_FOUND));

        // immutable: companyId & code
        e.setName(req.getName());
        e.setAddress(req.getAddress());
        e.setIsActive(req.getIsActive());

        return BranchResponse.from(e); // dirty checking
    }

    @Override
    public void delete(Long id) {
        BranchEntity e = branchRepo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.BRANCH_NOT_FOUND));
        e.setIsDeleted(true); // soft delete (dirty checking)
    }
}

