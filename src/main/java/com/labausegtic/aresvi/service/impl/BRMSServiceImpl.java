package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.BRMSService;
import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
            HttpResponse<String> response = Unirest.post("http://0.0.0.0:7000/api/inference")
                .header("content-type", "application/json")
                .header("cache-control", "no-cache")
                .header("postman-token", "e27ab633-b5ac-30d0-853d-9841a19abc19")
                .body("{\n   \"percentageNotRequired\": 0.8,\n   \"percentageLevel1\": 0.8,\n   \"percentageLevel2\": 0.8,\n   \"percentageLevel3\": 0.9,\n   \"percentageLevel4\": 0.8,\n   \"percentageLevel5\": 0.8,\n   \"pevelComputerization\": 3\n}")
                .asString();



            resultInferenceDTO.setCategory("J");

        } catch (UnirestException e) {
            e.printStackTrace();
            resultInferenceDTO.setCategory("J");
        }

        return resultInferenceDTO;
    }

}
