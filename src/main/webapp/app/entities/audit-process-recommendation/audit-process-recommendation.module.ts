import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditProcessRecommendationService,
    AuditProcessRecommendationPopupService,
    AuditProcessRecommendationComponent,
    AuditProcessRecommendationDetailComponent,
    AuditProcessRecommendationDialogComponent,
    AuditProcessRecommendationPopupComponent,
    AuditProcessRecommendationDeletePopupComponent,
    AuditProcessRecommendationDeleteDialogComponent,
    auditProcessRecommendationRoute,
    auditProcessRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...auditProcessRecommendationRoute,
    ...auditProcessRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditProcessRecommendationComponent,
        AuditProcessRecommendationDetailComponent,
        AuditProcessRecommendationDialogComponent,
        AuditProcessRecommendationDeleteDialogComponent,
        AuditProcessRecommendationPopupComponent,
        AuditProcessRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        AuditProcessRecommendationComponent,
        AuditProcessRecommendationDialogComponent,
        AuditProcessRecommendationPopupComponent,
        AuditProcessRecommendationDeleteDialogComponent,
        AuditProcessRecommendationDeletePopupComponent,
    ],
    providers: [
        AuditProcessRecommendationService,
        AuditProcessRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditProcessRecommendationModule {}
