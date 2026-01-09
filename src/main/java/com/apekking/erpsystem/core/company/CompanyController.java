package com.apekking.erpsystem.core.company;


import com.apekking.erpsystem.core.company.dto.CompanyCreateRequest;
import com.apekking.erpsystem.core.company.dto.CompanyResponse;
import com.apekking.erpsystem.core.company.dto.CompanyUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Company", description = "Company management API")
@RestController
@RequestMapping("/api/core/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @Operation(summary = "Create new company")
    @PostMapping
    public CompanyResponse create(@Valid @RequestBody CompanyCreateRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Get company by id")
    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all companies")
    @GetMapping
    public List<CompanyResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Update company")
    @PutMapping("/{id}")
    public CompanyResponse update(
            @PathVariable Long id,
            @Valid @RequestBody CompanyUpdateRequest req) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete company (soft delete)")
    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}


