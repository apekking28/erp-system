package com.apekking.erpsystem.core.company;

import com.apekking.erpsystem.core.company.dto.CompanyCreateRequest;
import com.apekking.erpsystem.core.company.dto.CompanyResponse;
import com.apekking.erpsystem.core.company.dto.CompanyUpdateRequest;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repo;

    public CompanyServiceImpl(CompanyRepository repo) {
        this.repo = repo;
    }

    @Override
    public CompanyResponse create(CompanyCreateRequest req) {
        if (repo.existsByCodeAndIsDeletedFalse(req.getCode())) {
            throw new BusinessException(ErrorCode.COMPANY_CODE_EXISTS);
        }

        CompanyEntity e = new CompanyEntity();
        e.setCode(req.getCode());
        e.setName(req.getName());
        e.setStatus("ACTIVE");
        e.setTimezone(req.getTimezone());
        e.setCurrency(req.getCurrency());

        return CompanyResponse.from(repo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getById(Long id) {
        CompanyEntity e = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));
        return CompanyResponse.from(e);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getAll() {
        return repo.findAll().stream()
                .filter(c -> !c.getIsDeleted())
                .map(CompanyResponse::from)
                .toList();
    }

    @Override
    public CompanyResponse update(Long id, CompanyUpdateRequest req) {
        CompanyEntity e = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));

        e.setName(req.getName());
        e.setStatus(req.getStatus());
        e.setTimezone(req.getTimezone());
        e.setCurrency(req.getCurrency());

        return CompanyResponse.from(e);
    }

    @Override
    public void delete(Long id) {
        CompanyEntity e = repo.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMPANY_NOT_FOUND));
        e.setIsDeleted(true);
    }
}

