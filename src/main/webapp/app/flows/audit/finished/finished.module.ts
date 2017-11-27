import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {FinishedComponent} from './finished.component';
import {flowAuditFinishedRoute, TraceabilityAuditResolvePagingParams} from './finished.route';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';
import {
    TraceabilityAuditCategorizeAgainAuditDialogComponent,
    TraceabilityAuditCategorizeAgainAuditPopupComponent
} from './traceability-audit-categorize-again-audit-dialog.component';

const ENTITY_STATES = [
    ...flowAuditFinishedRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        FinishedComponent,
        TraceabilityAuditCategorizeAgainAuditPopupComponent,
        TraceabilityAuditCategorizeAgainAuditDialogComponent
    ],
    entryComponents: [
        TraceabilityAuditCategorizeAgainAuditPopupComponent,
        TraceabilityAuditCategorizeAgainAuditDialogComponent
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditFinishedModule {}
