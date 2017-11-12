import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {AresViSearchTraceabilityAuditModule} from './traceability_audit/traceability_audit.module';
import {AresViReportLastTwoResultsModule} from './last_two_results/last_two_results.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AresViSearchTraceabilityAuditModule,
        AresViReportLastTwoResultsModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViReportModule {}
