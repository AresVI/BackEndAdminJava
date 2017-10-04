import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditTaskRecommendationService,
    AuditTaskRecommendationPopupService,
    AuditTaskRecommendationComponent,
    AuditTaskRecommendationDetailComponent,
    AuditTaskRecommendationDialogComponent,
    AuditTaskRecommendationPopupComponent,
    AuditTaskRecommendationDeletePopupComponent,
    AuditTaskRecommendationDeleteDialogComponent,
    auditTaskRecommendationRoute,
    auditTaskRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...auditTaskRecommendationRoute,
    ...auditTaskRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditTaskRecommendationComponent,
        AuditTaskRecommendationDetailComponent,
        AuditTaskRecommendationDialogComponent,
        AuditTaskRecommendationDeleteDialogComponent,
        AuditTaskRecommendationPopupComponent,
        AuditTaskRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        AuditTaskRecommendationComponent,
        AuditTaskRecommendationDialogComponent,
        AuditTaskRecommendationPopupComponent,
        AuditTaskRecommendationDeleteDialogComponent,
        AuditTaskRecommendationDeletePopupComponent,
    ],
    providers: [
        AuditTaskRecommendationService,
        AuditTaskRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditTaskRecommendationModule {}
