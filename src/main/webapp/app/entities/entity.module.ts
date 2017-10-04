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
import { AresViCategoryAttributeRecommendationModule } from './category-attribute-recommendation/category-attribute-recommendation.module';
import { AresViAttributeRecommendationModule } from './attribute-recommendation/attribute-recommendation.module';
import { AresViCategoryAttRecommendationModule } from './category-att-recommendation/category-att-recommendation.module';
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
        AresViCategoryAttributeRecommendationModule,
        AresViAttributeRecommendationModule,
        AresViCategoryAttRecommendationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViEntityModule {}
