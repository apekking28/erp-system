package com.apekking.erpsystem.core.branch;

import com.apekking.erpsystem.core.branch.dto.BranchCreateRequest;
import com.apekking.erpsystem.core.branch.dto.BranchResponse;
import com.apekking.erpsystem.core.branch.dto.BranchUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Branch", description = "Branch management API")
@RestController
@RequestMapping("/core/branches")
public class BranchController {

    private final BranchService service;

    public BranchController(BranchService service) {
        this.service = service;
    }

    @Operation(summary = "Create new branch")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchResponse create(@Valid @RequestBody BranchCreateRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Get branch by id")
    @GetMapping("/{id}")
    public BranchResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all branches")
    @GetMapping
    public List<BranchResponse> getAll(
            @RequestParam(required = false) Long companyId) {
        return service.getAll(companyId);
    }

    @Operation(summary = "Update branch")
    @PutMapping("/{id}")
    public BranchResponse update(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete branch (soft delete)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

