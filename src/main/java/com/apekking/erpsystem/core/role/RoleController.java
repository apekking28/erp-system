package com.apekking.erpsystem.core.role;

import com.apekking.erpsystem.core.role.dto.RoleCreateRequest;
import com.apekking.erpsystem.core.role.dto.RolePermissionUpdateRequest;
import com.apekking.erpsystem.core.role.dto.RoleResponse;
import com.apekking.erpsystem.core.role.dto.RoleUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Role", description = "Role management API")
@RestController
@RequestMapping("/core/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @Operation(summary = "Create new role")
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse create(@Valid @RequestBody RoleCreateRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Get role by id")
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all roles")
    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    @GetMapping
    public List<RoleResponse> getAll(@RequestParam Long companyId) {
        return service.getAll(companyId);
    }

    @Operation(summary = "Update role")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @PutMapping("/{id}")
    public RoleResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete role")
    @PreAuthorize("hasAuthority('ROLE_DELETE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Update role with permissions")
    @PreAuthorize("hasAuthority('ROLE_UPDATE')")
    @PutMapping("/{id}/permissions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePermissions(
            @PathVariable Long id,
            @Valid @RequestBody RolePermissionUpdateRequest req
    ) {
        service.updatePermissions(id, req);
    }

}

