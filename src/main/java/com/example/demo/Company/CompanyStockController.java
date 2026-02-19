package com.example.demo.Company;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company-stocks")
public class CompanyStockController {

    private final CompanyStockService service;

    public CompanyStockController(CompanyStockService service) {
        this.service = service;
    }

    @GetMapping("/{companyId}")
    public CompanyStockResponse get(@PathVariable Long companyId) {
        return service.getCompanyStocks(companyId);
    }
}
