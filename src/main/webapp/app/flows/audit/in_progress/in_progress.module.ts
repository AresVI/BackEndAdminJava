import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {InProgressComponent} from './in_progress.component';
import {flowAuditInProgressRoute, TraceabilityAuditResolvePagingParams} from './in_progress.route';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';

const ENTITY_STATES = [
    ...flowAuditInProgressRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        InProgressComponent
    ],
    entryComponents: [
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditInProgressModule {}
