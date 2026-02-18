package com.example.demo.Company;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CompanyStockResponse(
        Long id,
        String name,
        String country,
        String symbol,
        String website,
        String email,
        OffsetDateTime createdAt,
        BigDecimal marketCapitalization,
        BigDecimal shareOutstanding
) {}
