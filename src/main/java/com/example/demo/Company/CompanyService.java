package com.example.demo.Company;



import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company createCompany(Company company) {
        return repository.save(company);
    }

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    public Company updateCompany(Long id, Company updated) {
        Company existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        existing.setName(updated.getName());
        existing.setCountry(updated.getCountry());
        existing.setSymbol(updated.getSymbol());
        existing.setWebsite(updated.getWebsite());
        existing.setEmail(updated.getEmail());

        return repository.save(existing);
    }

    public static class CompanyStock {
    }
}