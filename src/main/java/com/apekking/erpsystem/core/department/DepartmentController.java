package com.apekking.erpsystem.core.department;

import com.apekking.erpsystem.core.department.dto.DepartmentCreateRequest;
import com.apekking.erpsystem.core.department.dto.DepartmentResponse;
import com.apekking.erpsystem.core.department.dto.DepartmentTreeResponse;
import com.apekking.erpsystem.core.department.dto.DepartmentUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Department", description = "Department management API")
@RestController
@RequestMapping("/core/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @Operation(summary = "Create new department")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse create(@Valid @RequestBody DepartmentCreateRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Get department by id")
    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all departments")
    @GetMapping
    public List<DepartmentResponse> getAll(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId) {
        return service.getAll(companyId, branchId);
    }

    @Operation(summary = "Update department")
    @PutMapping("/{id}")
    public DepartmentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentUpdateRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete department (soft delete)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Department tree")
    @GetMapping("/tree")
    public List<DepartmentTreeResponse> getTree(
            @RequestParam Long branchId) {
        return service.getTreeByBranch(branchId);
    }

}

