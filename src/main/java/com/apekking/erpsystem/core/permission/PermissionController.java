package com.apekking.erpsystem.core.permission;

import com.apekking.erpsystem.core.permission.dto.PermissionCreateRequest;
import com.apekking.erpsystem.core.permission.dto.PermissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Permission", description = "Permission management API")
@RestController
@RequestMapping("/core/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @Operation(summary = "Create new permissions")
    @PostMapping
    @PreAuthorize("hasAuthority('PERMISSION_CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse create(
            @Valid @RequestBody PermissionCreateRequest req
    ) {
        return service.create(req);
    }

    @Operation(summary = "Get permission by id")
    @PreAuthorize("hasAuthority('PERMISSION_VIEW')")
    @GetMapping("/{id}")
    public PermissionResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get all permissions")
    @PreAuthorize("hasAuthority('PERMISSION_VIEW')")
    @GetMapping
    public List<PermissionResponse> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Delete permission (soft delete)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISSION_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

