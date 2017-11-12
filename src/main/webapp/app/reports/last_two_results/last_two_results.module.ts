import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {AuditProcessComponent} from './audit_process.component';
import {CompanyService} from '../../entities/company/company.service';
import {reportLastTwoResultsRoute} from './last_two_results.route';

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
        AuditProcessComponent
    ],
    entryComponents: [
        AuditProcessComponent
    ],
    providers: [
        CompanyService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViReportLastTwoResultsModule {}
