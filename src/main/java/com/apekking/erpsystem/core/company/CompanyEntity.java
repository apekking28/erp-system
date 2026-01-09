package com.apekking.erpsystem.core.company;

import com.apekking.erpsystem.common.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 20)
    private String status; // ACTIVE / INACTIVE

    @Column(length = 50)
    private String timezone;

    @Column(length = 10)
    private String currency;

    // getters & setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
