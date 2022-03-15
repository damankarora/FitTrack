package com.example.exerciselogger.ExternalService.Nutrionix;

import com.example.exerciselogger.Configuration.DataSourceConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class FetchActivityDetails {

    private final RestTemplate restTemplate;

    private final String apiId;
    private final String apiKey;

    public FetchActivityDetails(RestTemplateBuilder restTemplateBuilder, DataSourceConfig dataSourceConfig) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiId = dataSourceConfig.getApiId();
        this.apiKey = dataSourceConfig.getApiKey();
    }

    public FetchActivityDetailsResponse fetchData(Map<String, Object> requestBody){
        String url = "https://trackapi.nutritionix.com/v2/natural/exercise";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("x-app-id", this.apiId);
        headers.set("x-app-key", this.apiKey);



        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<FetchActivityDetailsResponse> response = this.restTemplate.postForEntity(url, httpEntity, FetchActivityDetailsResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.out.println(response.getStatusCode().value());
            System.out.println("Request failed");
            return null;
        }

    }


}
