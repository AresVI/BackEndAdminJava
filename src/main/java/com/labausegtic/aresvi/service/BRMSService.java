package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;

public interface BRMSService {

    ResultInferenceDTO getCategory(InferenceParameterDTO inferenceParameterDTO);

}
