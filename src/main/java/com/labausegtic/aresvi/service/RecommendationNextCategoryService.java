package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;

import java.util.List;

public interface RecommendationNextCategoryService {

    List<AuditProcessRecommendationDTO> getRecommendationNextCategory(Long company_id);

}
