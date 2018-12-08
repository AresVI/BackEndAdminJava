package com.labausegtic.aresvi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labausegtic.aresvi.config.ApplicationProperties;
import com.labausegtic.aresvi.service.BRMSService;
import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.RecommendationParameterNextCategoryDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;
import com.labausegtic.aresvi.service.dto.ResultRecommendationNextCategoryDTO;
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

    private String BRMS_Host;
    private String BRMS_Port;

    public BRMSServiceImpl(ApplicationProperties applicationProperties) {
        this.BRMS_Host = applicationProperties.getDrools().getHost();
        this.BRMS_Port = applicationProperties.getDrools().getPort();
    }

    @Override
    public ResultInferenceDTO getCategory(InferenceParameterDTO inferenceParameterDTO) {

        ResultInferenceDTO resultInferenceDTO = new ResultInferenceDTO();
        
        try {
            HttpResponse<JsonNode> response = Unirest.post("http://" + BRMS_Host + ":" + BRMS_Port + "/api/inference")
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

    @Override
    public ResultRecommendationNextCategoryDTO getRecommendationNextCategory(RecommendationParameterNextCategoryDTO recommendationParameterNextCategoryDTO) {
        ResultRecommendationNextCategoryDTO resultRecommendationNextCategoryDTO = new ResultRecommendationNextCategoryDTO();

        try {
            HttpResponse<JsonNode> response = Unirest.post("http://" + BRMS_Host + ":" + BRMS_Port + "/api/next-level")
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .body(writeValue(recommendationParameterNextCategoryDTO))
                .asJson();

            resultRecommendationNextCategoryDTO.setLevel(response.getBody().getObject().getLong("level"));

        } catch (UnirestException | JSONException ignored) {
            resultRecommendationNextCategoryDTO.setLevel(null);
        }

        return resultRecommendationNextCategoryDTO;
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
