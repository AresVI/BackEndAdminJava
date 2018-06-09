import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AresViAttributeModule } from './attribute/attribute.module';
import { AresViCategoryAttributeModule } from './category-attribute/category-attribute.module';
import { AresViContainerModule } from './container/container.module';
import { AresViAuditTaskModule } from './audit-task/audit-task.module';
import { AresViAuditProcessModule } from './audit-process/audit-process.module';
import { AresViParticipantModule } from './participant/participant.module';
import { AresViWeightingModule } from './weighting/weighting.module';
import { AresViTraceabilityAuditModule } from './traceability-audit/traceability-audit.module';
import { AresViCompanyModule } from './company/company.module';
import { AresViRecommendationModule } from './recommendation/recommendation.module';
import { AresViAuditProcessRecommendationModule } from './audit-process-recommendation/audit-process-recommendation.module';
import { AresViAuditTaskRecommendationModule } from './audit-task-recommendation/audit-task-recommendation.module';
import { AresViAttributeRecommendationModule } from './attribute-recommendation/attribute-recommendation.module';
import { AresViCategoryAttrRecommendationModule } from './category-attr-recommendation/category-attr-recommendation.module';
import { AresViAuditorModule } from './auditor/auditor.module';
import { AresViCompanyContactPersonModule } from './company-contact-person/company-contact-person.module';
import { AresViCancellationTraceabilityAuditModule } from './cancellation-traceability-audit/cancellation-traceability-audit.module';
import { AresViCompanyAddressModule } from './company-address/company-address.module';
import { AresViAuditProcessStandardObservationModule } from './audit-process-standard-observation/audit-process-standard-observation.module';
import { AresViAuditTaskStandardObservationModule } from './audit-task-standard-observation/audit-task-standard-observation.module';
import { AresViAuditAttributeAnalysisModule } from './audit-attribute-analysis/audit-attribute-analysis.module';
import { AresViProductTypeModule } from './product-type/product-type.module';
import { AresViTraceAuditModule } from './trace-audit/trace-audit.module';
import { AresViProductModule } from './product/product.module';
import { AresViGlossaryModule } from './glossary/glossary.module';
import { AresViFaqsModule } from './faqs/faqs.module';
import { AresViRecommendationAttributeModule } from './recommendation-attribute/recommendation-attribute.module';
import { AresViRecommendationAttributeRecommendationModule } from './recommendation-attribute-recommendation/recommendation-attribute-recommendation.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AresViAttributeModule,
        AresViCategoryAttributeModule,
        AresViContainerModule,
        AresViAuditTaskModule,
        AresViAuditProcessModule,
        AresViParticipantModule,
        AresViWeightingModule,
        AresViTraceabilityAuditModule,
        AresViCompanyModule,
        AresViRecommendationModule,
        AresViAuditProcessRecommendationModule,
        AresViAuditTaskRecommendationModule,
        AresViAttributeRecommendationModule,
        AresViCategoryAttrRecommendationModule,
        AresViAuditorModule,
        AresViCompanyContactPersonModule,
        AresViCancellationTraceabilityAuditModule,
        AresViCompanyAddressModule,
        AresViAuditProcessStandardObservationModule,
        AresViAuditTaskStandardObservationModule,
        AresViAuditAttributeAnalysisModule,
        AresViProductTypeModule,
        AresViTraceAuditModule,
        AresViProductModule,
        AresViGlossaryModule,
        AresViFaqsModule,
        AresViRecommendationAttributeModule,
        AresViRecommendationAttributeRecommendationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViEntityModule {}
