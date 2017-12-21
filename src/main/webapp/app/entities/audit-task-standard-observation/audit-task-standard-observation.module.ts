import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditTaskStandardObservationService,
    AuditTaskStandardObservationPopupService,
    AuditTaskStandardObservationComponent,
    AuditTaskStandardObservationDetailComponent,
    AuditTaskStandardObservationDialogComponent,
    AuditTaskStandardObservationPopupComponent,
    AuditTaskStandardObservationDeletePopupComponent,
    AuditTaskStandardObservationDeleteDialogComponent,
    auditTaskStandardObservationRoute,
    auditTaskStandardObservationPopupRoute,
    AuditTaskStandardObservationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...auditTaskStandardObservationRoute,
    ...auditTaskStandardObservationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditTaskStandardObservationComponent,
        AuditTaskStandardObservationDetailComponent,
        AuditTaskStandardObservationDialogComponent,
        AuditTaskStandardObservationDeleteDialogComponent,
        AuditTaskStandardObservationPopupComponent,
        AuditTaskStandardObservationDeletePopupComponent,
    ],
    entryComponents: [
        AuditTaskStandardObservationComponent,
        AuditTaskStandardObservationDialogComponent,
        AuditTaskStandardObservationPopupComponent,
        AuditTaskStandardObservationDeleteDialogComponent,
        AuditTaskStandardObservationDeletePopupComponent,
    ],
    providers: [
        AuditTaskStandardObservationService,
        AuditTaskStandardObservationPopupService,
        AuditTaskStandardObservationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditTaskStandardObservationModule {}
