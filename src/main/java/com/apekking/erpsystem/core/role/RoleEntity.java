package com.apekking.erpsystem.core.role;

import com.apekking.erpsystem.common.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_role_company_code",
                        columnNames = {"company_id", "code"}
                )
        }
)
public class RoleEntity extends BaseEntity {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(nullable = false, length = 50)
    private String code; // ADMIN, HR, etc

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    // getters & setters
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

