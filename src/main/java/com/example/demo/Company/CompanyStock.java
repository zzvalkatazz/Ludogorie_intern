package com.example.demo.Company;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(
        name = "company_stocks",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_company_date",
                columnNames = {"company_id", "fetched_date"}
        )
)
public class CompanyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private BigDecimal marketCapitalization;

    @Column(nullable = false)
    private BigDecimal shareOutstanding;

    @Column(nullable = false)
    private LocalDate fetchedDate;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public BigDecimal getMarketCapitalization() {
        return marketCapitalization;
    }

    public BigDecimal getShareOutstanding() {
        return shareOutstanding;
    }

    public LocalDate getFetchedDate() {
        return fetchedDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setMarketCapitalization(BigDecimal marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public void setShareOutstanding(BigDecimal shareOutstanding) {
        this.shareOutstanding = shareOutstanding;
    }

    public void setFetchedDate(LocalDate fetchedDate) {
        this.fetchedDate = fetchedDate;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
