package com.bituan.hng14_stage_1.service;

import com.bituan.hng14_stage_1.exception.ExternalApiException;
import com.bituan.hng14_stage_1.model.AgifyApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AgifyApiService {
    public AgifyApiResponse classifyName (String name) {
        RestClient restClient = RestClient.create();

        ResponseEntity<AgifyApiResponse> response = restClient
                .get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new ExternalApiException(502, "Agify returned an invalid response");
                })
                .toEntity(AgifyApiResponse.class);

        if (!response.hasBody() || new Integer(response.getBody().getAge()) == null) {
            throw new ExternalApiException(502, "Agify returned an invalid response");
        }

        return response.getBody();
    }
}
