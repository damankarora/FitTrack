package com.activitytracker.foodlogger.ExternalService;

import com.activitytracker.foodlogger.Configuration.DataSourceConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class FetchNutrients {

    private final RestTemplate restTemplate;

    private final String apiId;
    private final String apiKey;

    public FetchNutrients(RestTemplateBuilder restTemplateBuilder, DataSourceConfig dataSourceConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiId = dataSourceConfig.getApiId();
        this.apiKey = dataSourceConfig.getApiKey();
    }

    public FetchNutrientsResponse fetchData(String foods){
        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("x-app-id", this.apiId);
        headers.set("x-app-key", this.apiKey);

        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put("query", foods);

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(objectMap, headers);

        ResponseEntity<FetchNutrientsResponse> response = this.restTemplate.postForEntity(url, httpEntity, FetchNutrientsResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.out.println(response.getStatusCode().value());
            System.out.println("Request failed");
            return null;
        }

    }
}
