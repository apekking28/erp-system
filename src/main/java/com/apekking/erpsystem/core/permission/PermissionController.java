package com.apekking.erpsystem.core.permission;

import com.apekking.erpsystem.core.permission.dto.PermissionCreateRequest;
import com.apekking.erpsystem.core.permission.dto.PermissionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core/permissions")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse create(
            @Valid @RequestBody PermissionCreateRequest req
    ) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PermissionResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<PermissionResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

