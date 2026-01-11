package com.apekking.erpsystem.core.branch;

import com.apekking.erpsystem.core.branch.dto.BranchCreateRequest;
import com.apekking.erpsystem.core.branch.dto.BranchResponse;
import com.apekking.erpsystem.core.branch.dto.BranchUpdateRequest;

import java.util.List;

public interface BranchService {

    BranchResponse create(BranchCreateRequest request);

    BranchResponse getById(Long id);

    List<BranchResponse> getAll(Long companyId);

    BranchResponse update(Long id, BranchUpdateRequest request);

    void delete(Long id);
}
