package com.apekking.erpsystem.core.branch;
;
import com.apekking.erpsystem.common.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "branches",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_branch_company_code", columnNames = {"company_id", "code"})
        }
)
public class BranchEntity extends BaseEntity {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // getters & setters
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }
}

