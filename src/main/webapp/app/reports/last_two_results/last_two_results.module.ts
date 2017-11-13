import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {AuditProcessComponent} from './audit_process.component';
import {CompanyService} from '../../entities/company/company.service';
import {reportLastTwoResultsRoute} from './last_two_results.route';
import {AuditProcessService} from '../../entities/audit-process/audit-process.service';
import {AuditTasksComponent} from './audit_tasks.component';

const ENTITY_STATES = [
    ...reportLastTwoResultsRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        AuditProcessComponent,
        AuditTasksComponent
    ],
    entryComponents: [
        AuditProcessComponent,
        AuditTasksComponent
    ],
    providers: [
        CompanyService,
        AuditProcessService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViReportLastTwoResultsModule {}
