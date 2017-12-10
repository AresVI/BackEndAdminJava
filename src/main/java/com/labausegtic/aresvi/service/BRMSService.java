package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.RecommendationParameterNextCategoryDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;
import com.labausegtic.aresvi.service.dto.ResultRecommendationNextCategoryDTO;

public interface BRMSService {

    ResultInferenceDTO getCategory(InferenceParameterDTO inferenceParameterDTO);

    ResultRecommendationNextCategoryDTO getRecommendationNextCategory(RecommendationParameterNextCategoryDTO recommendationParameterNextCategoryDTO);

}
