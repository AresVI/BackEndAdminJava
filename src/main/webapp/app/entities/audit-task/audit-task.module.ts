import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AuditTaskService,
    AuditTaskPopupService,
    AuditTaskComponent,
    AuditTaskDetailComponent,
    AuditTaskDialogComponent,
    AuditTaskPopupComponent,
    AuditTaskDeletePopupComponent,
    AuditTaskDeleteDialogComponent,
    auditTaskRoute,
    auditTaskPopupRoute,
    AuditTaskResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...auditTaskRoute,
    ...auditTaskPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditTaskComponent,
        AuditTaskDetailComponent,
        AuditTaskDialogComponent,
        AuditTaskDeleteDialogComponent,
        AuditTaskPopupComponent,
        AuditTaskDeletePopupComponent,
    ],
    entryComponents: [
        AuditTaskComponent,
        AuditTaskDialogComponent,
        AuditTaskPopupComponent,
        AuditTaskDeleteDialogComponent,
        AuditTaskDeletePopupComponent,
    ],
    providers: [
        AuditTaskService,
        AuditTaskPopupService,
        AuditTaskResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditTaskModule {}
