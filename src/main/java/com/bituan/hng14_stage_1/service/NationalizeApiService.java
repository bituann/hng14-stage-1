package com.bituan.hng14_stage_1.service;

import com.bituan.hng14_stage_1.exception.ExternalApiException;
import com.bituan.hng14_stage_1.model.NationalizeApiResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class NationalizeService {
    public NationalizeApiResponse classifyName (String name) {
        RestClient restClient = RestClient.create();

        ResponseEntity<NationalizeApiResponse> response = restClient
                .get()
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    throw new ExternalApiException(502, "Nationalize returned an invalid response");
                })
                .toEntity(NationalizeApiResponse.class);

        if (!response.hasBody() || response.getBody().getCountry().isEmpty() || response.getBody().getCountry() == null) {
            throw new ExternalApiException(502, "Nationalize returned an invalid response");
        }

        return response.getBody();
    }
}
