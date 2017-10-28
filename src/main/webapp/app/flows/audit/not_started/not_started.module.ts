import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {NotStartedComponent} from './not_started.component';
import {flowAuditNotStartedRoute, TraceabilityAuditResolvePagingParams} from './not_started.route';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';
import {
    TraceabilityAuditStartAuditDialogComponent,
    TraceabilityAuditStartAuditPopupComponent
} from './traceability-audit-start-audit-dialog.component';

const ENTITY_STATES = [
    ...flowAuditNotStartedRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        NotStartedComponent,
        TraceabilityAuditStartAuditDialogComponent,
        TraceabilityAuditStartAuditPopupComponent
    ],
    entryComponents: [
        TraceabilityAuditStartAuditDialogComponent,
        TraceabilityAuditStartAuditPopupComponent
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditNotStartedModule {}
