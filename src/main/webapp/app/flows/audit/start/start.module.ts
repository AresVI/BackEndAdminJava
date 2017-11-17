import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';
import {flowAuditStartRoute} from './start.route';
import {StartComponent} from './start.component';
import {TraceabilityAuditService} from './traceability-audit.service';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {CompanyService} from '../../../entities/company/company.service';
import {CompanyContactPersonService} from '../../../entities/company-contact-person/company-contact-person.service';
import {CompanyAuditStartPopupService} from './company-popup.service';
import {
    CompanyContactPersonAuditStartDialogComponent, CompanyContactPersonAuditStartPopupComponent
} from './company-contact-person-dialog.component';
import {CompanyContactPersonAuditStartPopupService} from './company-contact-person-popup.service';
import {CompanyAuditStartPopupComponent, CompanyDialogAuditStartComponent} from './company-dialog.component';

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
        CompanyAuditStartPopupComponent,
        CompanyContactPersonAuditStartPopupComponent,
        CompanyContactPersonAuditStartDialogComponent
    ],
    entryComponents: [
        StartComponent,
        CompanyDialogAuditStartComponent,
        CompanyAuditStartPopupComponent,
        CompanyContactPersonAuditStartDialogComponent,
        CompanyContactPersonAuditStartPopupComponent
    ],
    providers: [
        CompanyService,
        CompanyContactPersonService,
        TraceabilityAuditService,
        CompanyAuditStartPopupService,
        CompanyContactPersonAuditStartPopupService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditStartModule {}
