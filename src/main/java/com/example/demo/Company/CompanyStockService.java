package com.example.demo.Company;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CompanyStockService {

    private final CompanyRepository companyRepository;
    private final CompanyStockRepository companyStockRepository;
    private final FinnhubClient finnhubClient;

    public CompanyStockService(CompanyRepository companyRepository,
                                CompanyStockRepository companyStockRepository,
                                FinnhubClient finnhubClient) {
        this.companyRepository = companyRepository;
        this.companyStockRepository = companyStockRepository;
        this.finnhubClient = finnhubClient;
    }

    @Transactional
    public CompanyStockResponse getCompanyStocks(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found: " + companyId));

        LocalDate today = LocalDate.now();

        CompanyStock stock = companyStockRepository
                .findByCompanyIdAndFetchedDate(companyId, today)
                .orElseGet(() -> {
                    var fin = finnhubClient.fetchProfile2(company.getSymbol());

                    if (fin == null || fin.marketCapitalization() == null || fin.shareOutstanding() == null) {
                        throw new RuntimeException("Finnhub returned empty data for symbol=" + company.getSymbol());
                    }

                    CompanyStock s = new CompanyStock();
                    s.setCompany(company);
                    s.setFetchedDate(today);
                    s.setMarketCapitalization(fin.marketCapitalization());
                    s.setShareOutstanding(fin.shareOutstanding());

                    return companyStockRepository.save(s); // INSERT (история)
                });

        return new CompanyStockResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getSymbol(),
                company.getWebsite(),
                company.getEmail(),
                company.getCreatedAt(),
                stock.getMarketCapitalization(),
                stock.getShareOutstanding()
        );
    }
}
