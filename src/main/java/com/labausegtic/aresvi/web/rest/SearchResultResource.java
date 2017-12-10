package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.RecommendationService;
import com.labausegtic.aresvi.service.CompanyService;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.dto.CompanyDTO;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller for managing Recommendation.
 */
@RestController
@RequestMapping("/api")
public class SearchResultResource {

    private final Logger log = LoggerFactory.getLogger(SearchResultResource.class);

    private static final String ENTITY_NAME = "recommendation";

    private final RecommendationService recommendationService;
    private final CompanyService companyService;
    private final TraceabilityAuditService traceabilityAuditService;

    public SearchResultResource(RecommendationService recommendationService, CompanyService companyService, TraceabilityAuditService traceabilityAuditService) {
        this.recommendationService = recommendationService;
        this.companyService = companyService;
        this.traceabilityAuditService = traceabilityAuditService;
    }

    @GetMapping("/search_result/{identification}")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> searchResult(@PathVariable("identification") String identification) {
        log.debug("REST request to search a result : {}", identification);

        CompanyDTO company = companyService.findOneByIdentification(identification);

        TraceabilityAuditDTO traceabilityAudit = traceabilityAuditService.findLastByCompanyId(company.getId());

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(traceabilityAudit));
    }
}
