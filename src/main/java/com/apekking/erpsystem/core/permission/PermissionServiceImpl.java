package com.apekking.erpsystem.core.permission;

import com.apekking.erpsystem.core.permission.dto.PermissionCreateRequest;
import com.apekking.erpsystem.core.permission.dto.PermissionResponse;
import com.apekking.erpsystem.exception.BusinessException;
import com.apekking.erpsystem.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repo;

    public PermissionServiceImpl(PermissionRepository repo) {
        this.repo = repo;
    }

    @Override
    public PermissionResponse create(PermissionCreateRequest req) {

        if (repo.existsByCode(req.code)) {
            throw new BusinessException(ErrorCode.PERMISSION_CODE_EXISTS);
        }

        PermissionEntity e = new PermissionEntity();
        e.setCode(req.code);
        e.setDescription(req.description);

        return PermissionResponse.from(repo.save(e));
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponse getById(Long id) {
        return repo.findById(id)
                .map(PermissionResponse::from)
                .orElseThrow(() -> new BusinessException(ErrorCode.PERMISSION_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(PermissionResponse::from)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new BusinessException(ErrorCode.PERMISSION_NOT_FOUND);
        }
        repo.deleteById(id); // HARD DELETE
    }
}

