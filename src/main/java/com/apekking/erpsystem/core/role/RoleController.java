package com.apekking.erpsystem.core.role;

import com.apekking.erpsystem.core.role.dto.RoleCreateRequest;
import com.apekking.erpsystem.core.role.dto.RolePermissionUpdateRequest;
import com.apekking.erpsystem.core.role.dto.RoleResponse;
import com.apekking.erpsystem.core.role.dto.RoleUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/core/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse create(@Valid @RequestBody RoleCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<RoleResponse> getAll(@RequestParam Long companyId) {
        return service.getAll(companyId);
    }

    @PutMapping("/{id}")
    public RoleResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/permissions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePermissions(
            @PathVariable Long id,
            @Valid @RequestBody RolePermissionUpdateRequest req
    ) {
        service.updatePermissions(id, req);
    }

}

