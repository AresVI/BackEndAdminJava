import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    TraceAuditService,
    TraceAuditPopupService,
    TraceAuditComponent,
    TraceAuditDetailComponent,
    TraceAuditDialogComponent,
    TraceAuditPopupComponent,
    TraceAuditDeletePopupComponent,
    TraceAuditDeleteDialogComponent,
    traceAuditRoute,
    traceAuditPopupRoute,
} from './';

const ENTITY_STATES = [
    ...traceAuditRoute,
    ...traceAuditPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TraceAuditComponent,
        TraceAuditDetailComponent,
        TraceAuditDialogComponent,
        TraceAuditDeleteDialogComponent,
        TraceAuditPopupComponent,
        TraceAuditDeletePopupComponent,
    ],
    entryComponents: [
        TraceAuditComponent,
        TraceAuditDialogComponent,
        TraceAuditPopupComponent,
        TraceAuditDeleteDialogComponent,
        TraceAuditDeletePopupComponent,
    ],
    providers: [
        TraceAuditService,
        TraceAuditPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViTraceAuditModule {}
