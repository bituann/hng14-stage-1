package com.bituan.hng14_stage_1.service;

import com.bituan.hng14_stage_1.exception.ExternalApiException;
import com.bituan.hng14_stage_1.model.GenderizeApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class GenderizeApiService {
    public GenderizeApiResponse classifyName (String name) {
        RestClient restClient = RestClient.create();

        ResponseEntity<GenderizeApiResponse> response = restClient
                .get()
                .uri("https://api.genderize.io?name=" + name)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new ExternalApiException(502, "Genderize returned an invalid response");
                })
                .toEntity(GenderizeApiResponse.class);

        if (!response.hasBody()) {
            throw new ExternalApiException(502, "Genderize returned an invalid response");
        }

        if (response.getBody().getCount() <= 0 || response.getBody().getGender() == null) {
            throw new ExternalApiException(502, "Genderize returned an invalid response");
        }

        return response.getBody();
    }
}
