import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditProcessService,
    AuditProcessPopupService,
    AuditProcessComponent,
    AuditProcessDetailComponent,
    AuditProcessDialogComponent,
    AuditProcessPopupComponent,
    AuditProcessDeletePopupComponent,
    AuditProcessDeleteDialogComponent,
    auditProcessRoute,
    auditProcessPopupRoute,
    AuditProcessResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...auditProcessRoute,
    ...auditProcessPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditProcessComponent,
        AuditProcessDetailComponent,
        AuditProcessDialogComponent,
        AuditProcessDeleteDialogComponent,
        AuditProcessPopupComponent,
        AuditProcessDeletePopupComponent,
    ],
    entryComponents: [
        AuditProcessComponent,
        AuditProcessDialogComponent,
        AuditProcessPopupComponent,
        AuditProcessDeleteDialogComponent,
        AuditProcessDeletePopupComponent,
    ],
    providers: [
        AuditProcessService,
        AuditProcessPopupService,
        AuditProcessResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditProcessModule {}
