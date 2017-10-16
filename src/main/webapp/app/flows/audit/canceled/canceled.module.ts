import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {CanceledComponent} from './canceled.component';
import {flowAuditCanceledRoute, TraceabilityAuditResolvePagingParams} from './canceled.route';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';

const ENTITY_STATES = [
    ...flowAuditCanceledRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        CanceledComponent
    ],
    entryComponents: [
    ],
    providers: [
        TraceabilityAuditService,
        TraceabilityAuditResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditCanceledModule {}
