package com.apekking.erpsystem.core.user;

import com.apekking.erpsystem.core.user.dto.UserCreateRequest;
import com.apekking.erpsystem.core.user.dto.UserResponse;
import com.apekking.erpsystem.core.user.dto.UserRoleUpdateRequest;
import com.apekking.erpsystem.core.user.dto.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User management API")
@RestController
@RequestMapping("/core/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Create new user")
    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserCreateRequest req) {
        return service.create(req);
    }

    @Operation(summary = "Get user by id")
    @PreAuthorize("hasAuthority('USER_VIEW')")
    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Get user all users")
    @PreAuthorize("hasAuthority('USER_VIEW')")
    @GetMapping
    public List<UserResponse> getAll(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long branchId,
            @RequestParam(required = false) Long departmentId
    ) {
        return service.getAll(companyId, branchId, departmentId);
    }

    @Operation(summary = "Update user")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest req
    ) {
        return service.update(id, req);
    }

    @Operation(summary = "Delete user")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Update user with roles")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PutMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoles(
            @PathVariable Long id,
            @Valid @RequestBody UserRoleUpdateRequest req
    ) {
        service.updateRoles(id, req);
    }

}

