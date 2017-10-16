import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {AresViFlowAuditStartModule} from './audit/start/start.module';
import {AresViFlowAuditDashboardModule} from './audit/dashboard/dashboard.module';
import {AresViFlowAuditNotStartedModule} from './audit/not_started/not_started.module';
import {AresViFlowAuditInProgressModule} from './audit/in_progress/in_progress.module';
import {AresViFlowAuditFinishedModule} from './audit/finished/finished.module';
import {AresViFlowAuditCanceledModule} from './audit/canceled/canceled.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        AresViFlowAuditStartModule,
        AresViFlowAuditDashboardModule,
        AresViFlowAuditNotStartedModule,
        AresViFlowAuditInProgressModule,
        AresViFlowAuditFinishedModule,
        AresViFlowAuditCanceledModule
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowModule {}
