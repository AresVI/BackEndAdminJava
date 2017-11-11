import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {AresViSearchTraceabilityAuditModule} from './traceability_audit/traceability_audit.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AresViSearchTraceabilityAuditModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViSearchModule {}
