package com.example.demo.service;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
public class RestScheduleService implements ScheduleService {

    private RestTemplate restTemplate;

    public RestScheduleService(String accessToken) {
        restTemplate = new RestTemplate();
        if (accessToken != null) {
            restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
            restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        }
    }

    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + accessToken);
            return execution.execute(request, body);
        };
        return interceptor;
    }

    @Override
    public String testSchedule() {
        return restTemplate.getForObject("http://localhost:8080/api/v1/welcome", String.class);
    }

}
