
package com.example.demo.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CompanyStockRepository extends JpaRepository<CompanyStock, Long> {
    Optional<CompanyStock> findByCompanyIdAndFetchedDate(Long companyId, LocalDate fetchedDate);
}
