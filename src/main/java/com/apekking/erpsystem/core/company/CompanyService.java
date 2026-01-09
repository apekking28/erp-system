package com.apekking.erpsystem.core.company;

import com.apekking.erpsystem.core.company.dto.CompanyCreateRequest;
import com.apekking.erpsystem.core.company.dto.CompanyResponse;
import com.apekking.erpsystem.core.company.dto.CompanyUpdateRequest;

import java.util.List;

public interface CompanyService {

    CompanyResponse create(CompanyCreateRequest request);

    CompanyResponse getById(Long id);

    List<CompanyResponse> getAll();

    CompanyResponse update(Long id, CompanyUpdateRequest request);

    void delete(Long id);
}

