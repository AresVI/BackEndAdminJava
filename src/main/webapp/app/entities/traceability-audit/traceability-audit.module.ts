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
    ],
    entryComponents: [
        TraceabilityAuditComponent,
        TraceabilityAuditDialogComponent,
        TraceabilityAuditPopupComponent,
        TraceabilityAuditDeleteDialogComponent,
        TraceabilityAuditDeletePopupComponent,
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditPopupService,
        TraceabilityAuditResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViTraceabilityAuditModule {}
