import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {SearchTraceabilityAuditComponent} from './traceability_audit.component';
import {searchTraceabilityAuditRoute, SearchTraceabilityResolvePagingParams} from './traceability_audit.route';
import {TraceabilityAuditService} from '../../entities/traceability-audit/traceability-audit.service';

const ENTITY_STATES = [
    ...searchTraceabilityAuditRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        SearchTraceabilityAuditComponent
    ],
    entryComponents: [
    ],
    providers: [
        TraceabilityAuditService,
        SearchTraceabilityResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViSearchTraceabilityAuditModule {}
