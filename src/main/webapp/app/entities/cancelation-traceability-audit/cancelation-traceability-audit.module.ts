import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import { AresViAdminModule } from '../../admin/admin.module';
import {
    CancelationTraceabilityAuditService,
    CancelationTraceabilityAuditPopupService,
    CancelationTraceabilityAuditComponent,
    CancelationTraceabilityAuditDetailComponent,
    CancelationTraceabilityAuditDialogComponent,
    CancelationTraceabilityAuditPopupComponent,
    CancelationTraceabilityAuditDeletePopupComponent,
    CancelationTraceabilityAuditDeleteDialogComponent,
    cancelationTraceabilityAuditRoute,
    cancelationTraceabilityAuditPopupRoute,
    CancelationTraceabilityAuditResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...cancelationTraceabilityAuditRoute,
    ...cancelationTraceabilityAuditPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        AresViAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CancelationTraceabilityAuditComponent,
        CancelationTraceabilityAuditDetailComponent,
        CancelationTraceabilityAuditDialogComponent,
        CancelationTraceabilityAuditDeleteDialogComponent,
        CancelationTraceabilityAuditPopupComponent,
        CancelationTraceabilityAuditDeletePopupComponent,
    ],
    entryComponents: [
        CancelationTraceabilityAuditComponent,
        CancelationTraceabilityAuditDialogComponent,
        CancelationTraceabilityAuditPopupComponent,
        CancelationTraceabilityAuditDeleteDialogComponent,
        CancelationTraceabilityAuditDeletePopupComponent,
    ],
    providers: [
        CancelationTraceabilityAuditService,
        CancelationTraceabilityAuditPopupService,
        CancelationTraceabilityAuditResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCancelationTraceabilityAuditModule {}
