import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import { AresViAdminModule } from '../../admin/admin.module';
import {
    CancellationTraceabilityAuditService,
    CancellationTraceabilityAuditPopupService,
    CancellationTraceabilityAuditComponent,
    CancellationTraceabilityAuditDetailComponent,
    CancellationTraceabilityAuditDialogComponent,
    CancellationTraceabilityAuditPopupComponent,
    CancellationTraceabilityAuditDeletePopupComponent,
    CancellationTraceabilityAuditDeleteDialogComponent,
    cancellationTraceabilityAuditRoute,
    cancellationTraceabilityAuditPopupRoute,
    CancellationTraceabilityAuditResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...cancellationTraceabilityAuditRoute,
    ...cancellationTraceabilityAuditPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        AresViAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CancellationTraceabilityAuditComponent,
        CancellationTraceabilityAuditDetailComponent,
        CancellationTraceabilityAuditDialogComponent,
        CancellationTraceabilityAuditDeleteDialogComponent,
        CancellationTraceabilityAuditPopupComponent,
        CancellationTraceabilityAuditDeletePopupComponent,
    ],
    entryComponents: [
        CancellationTraceabilityAuditComponent,
        CancellationTraceabilityAuditDialogComponent,
        CancellationTraceabilityAuditPopupComponent,
        CancellationTraceabilityAuditDeleteDialogComponent,
        CancellationTraceabilityAuditDeletePopupComponent,
    ],
    providers: [
        CancellationTraceabilityAuditService,
        CancellationTraceabilityAuditPopupService,
        CancellationTraceabilityAuditResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCancellationTraceabilityAuditModule {}
