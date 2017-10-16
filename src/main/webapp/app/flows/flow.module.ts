import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {AresViFlowAuditStartModule} from './audit/start/start.module';
import {AresViFlowAuditDashboardModule} from './audit/dashboard/dashboard.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AresViFlowAuditStartModule,
        AresViFlowAuditDashboardModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowModule {}
