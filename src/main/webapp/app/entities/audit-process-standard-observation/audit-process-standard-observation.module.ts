import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditProcessStandardObservationService,
    AuditProcessStandardObservationPopupService,
    AuditProcessStandardObservationComponent,
    AuditProcessStandardObservationDetailComponent,
    AuditProcessStandardObservationDialogComponent,
    AuditProcessStandardObservationPopupComponent,
    AuditProcessStandardObservationDeletePopupComponent,
    AuditProcessStandardObservationDeleteDialogComponent,
    auditProcessStandardObservationRoute,
    auditProcessStandardObservationPopupRoute,
    AuditProcessStandardObservationResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...auditProcessStandardObservationRoute,
    ...auditProcessStandardObservationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditProcessStandardObservationComponent,
        AuditProcessStandardObservationDetailComponent,
        AuditProcessStandardObservationDialogComponent,
        AuditProcessStandardObservationDeleteDialogComponent,
        AuditProcessStandardObservationPopupComponent,
        AuditProcessStandardObservationDeletePopupComponent,
    ],
    entryComponents: [
        AuditProcessStandardObservationComponent,
        AuditProcessStandardObservationDialogComponent,
        AuditProcessStandardObservationPopupComponent,
        AuditProcessStandardObservationDeleteDialogComponent,
        AuditProcessStandardObservationDeletePopupComponent,
    ],
    providers: [
        AuditProcessStandardObservationService,
        AuditProcessStandardObservationPopupService,
        AuditProcessStandardObservationResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditProcessStandardObservationModule {}
