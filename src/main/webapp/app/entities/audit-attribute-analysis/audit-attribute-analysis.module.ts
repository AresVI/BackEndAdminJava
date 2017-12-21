import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditAttributeAnalysisService,
    AuditAttributeAnalysisPopupService,
    AuditAttributeAnalysisComponent,
    AuditAttributeAnalysisDetailComponent,
    AuditAttributeAnalysisDialogComponent,
    AuditAttributeAnalysisPopupComponent,
    AuditAttributeAnalysisDeletePopupComponent,
    AuditAttributeAnalysisDeleteDialogComponent,
    auditAttributeAnalysisRoute,
    auditAttributeAnalysisPopupRoute,
} from './';

const ENTITY_STATES = [
    ...auditAttributeAnalysisRoute,
    ...auditAttributeAnalysisPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditAttributeAnalysisComponent,
        AuditAttributeAnalysisDetailComponent,
        AuditAttributeAnalysisDialogComponent,
        AuditAttributeAnalysisDeleteDialogComponent,
        AuditAttributeAnalysisPopupComponent,
        AuditAttributeAnalysisDeletePopupComponent,
    ],
    entryComponents: [
        AuditAttributeAnalysisComponent,
        AuditAttributeAnalysisDialogComponent,
        AuditAttributeAnalysisPopupComponent,
        AuditAttributeAnalysisDeleteDialogComponent,
        AuditAttributeAnalysisDeletePopupComponent,
    ],
    providers: [
        AuditAttributeAnalysisService,
        AuditAttributeAnalysisPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditAttributeAnalysisModule {}
