package com.example.demo.Company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class FinnhubClient {

    private final WebClient webClient;
    private final String apiKey;

    public FinnhubClient(WebClient.Builder builder,
                         @Value("${finnhub.base-url}") String baseUrl,
                         @Value("${finnhub.api-key}") String apiKey) {
        this.webClient = builder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
    }

    public FinnhubProfile2Response fetchProfile2(String symbol) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stock/profile2")
                        .queryParam("symbol", symbol)
                        .queryParam("token", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(FinnhubProfile2Response.class)
                .block();
    }

    public record FinnhubProfile2Response(
            BigDecimal marketCapitalization,
            BigDecimal shareOutstanding
    ) {}
}
