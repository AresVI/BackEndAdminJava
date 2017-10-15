import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';
import {flowAuditStartRoute} from './start.route';
import {StartComponent} from './start.component';
import {TraceabilityAuditService} from './traceability-audit.service';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {AuditorService} from '../../../entities/auditor/auditor.service';
import {CompanyService} from '../../../entities/company/company.service';
import {CompanyPopupService} from '../../../entities/company/company-popup.service';
import {CompanyDialogAuditStartComponent, CompanyPopupComponent} from './company-dialog.component';

const ENTITY_STATES = [
    ...flowAuditStartRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        StartComponent,
        CompanyDialogAuditStartComponent,
        CompanyPopupComponent
    ],
    entryComponents: [
        CompanyDialogAuditStartComponent,
        CompanyPopupComponent
    ],
    providers: [
        CompanyService,
        CompanyPopupService,
        AuditorService,
        TraceabilityAuditService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditStartModule {}
