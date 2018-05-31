import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    TraceabilityAuditService,
    TraceabilityAuditPopupService,
    TraceabilityAuditComponent,
    TraceabilityAuditDetailComponent,
    TraceabilityAuditDialogComponent,
    TraceabilityAuditPopupComponent,
    TraceabilityAuditDeletePopupComponent,
    TraceabilityAuditDeleteDialogComponent,
    traceabilityAuditRoute,
    traceabilityAuditPopupRoute,
    TraceabilityAuditResolvePagingParams,
} from './';
import {
    TraceabilityAuditFinishAuditDialogComponent,
    TraceabilityAuditFinishAuditPopupComponent
} from './traceability-audit-finish-audit-dialog.component';

const ENTITY_STATES = [
    ...traceabilityAuditRoute,
    ...traceabilityAuditPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TraceabilityAuditComponent,
        TraceabilityAuditDetailComponent,
        TraceabilityAuditDialogComponent,
        TraceabilityAuditDeleteDialogComponent,
        TraceabilityAuditPopupComponent,
        TraceabilityAuditDeletePopupComponent,
        TraceabilityAuditFinishAuditDialogComponent,
        TraceabilityAuditFinishAuditPopupComponent
    ],
    entryComponents: [
        TraceabilityAuditComponent,
        TraceabilityAuditDialogComponent,
        TraceabilityAuditPopupComponent,
        TraceabilityAuditDeleteDialogComponent,
        TraceabilityAuditDeletePopupComponent,
        TraceabilityAuditFinishAuditDialogComponent,
        TraceabilityAuditFinishAuditPopupComponent
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditPopupService,
        TraceabilityAuditResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViTraceabilityAuditModule {}
