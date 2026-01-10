package com.apekking.erpsystem.core.permission;

import jakarta.persistence.*;

@Entity
@Table(
        name = "permissions",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_permission_code", columnNames = "code")
        }
)
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String code; // USER_CREATE, ROLE_UPDATE, etc

    @Column(nullable = false, length = 255)
    private String description;

    // getters & setters
    public Long getId() { return id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

