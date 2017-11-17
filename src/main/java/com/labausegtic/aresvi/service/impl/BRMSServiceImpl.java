package com.labausegtic.aresvi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labausegtic.aresvi.service.BRMSService;
import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BRMSServiceImpl implements BRMSService {

    private final Logger log = LoggerFactory.getLogger(BRMSServiceImpl.class);

    @Override
    public ResultInferenceDTO getCategory(InferenceParameterDTO inferenceParameterDTO) {

        ResultInferenceDTO resultInferenceDTO = new ResultInferenceDTO();

        try {
            HttpResponse<JsonNode> response = Unirest.post("http://165.227.89.229:7000/api/inference")
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .body(writeValue(inferenceParameterDTO))
                .asJson();

            resultInferenceDTO.setCategory(response.getBody().getObject().getString("category"));

        } catch (UnirestException | JSONException e) {
            e.printStackTrace();
            resultInferenceDTO.setCategory("");
        }

        return resultInferenceDTO;
    }

    private String writeValue(Object value) {
        try {
            ObjectMapper jacksonObjectMapper = new ObjectMapper();
            return jacksonObjectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
