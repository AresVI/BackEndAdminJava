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
import {AresViCategoryAttributeModule} from '../category-attribute/category-attribute.module';

const ENTITY_STATES = [
    ...auditTaskRoute,
    ...auditTaskPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        AresViCategoryAttributeModule,
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
    exports: [
        AuditTaskComponent,
        AuditTaskDetailComponent,
        AuditTaskDialogComponent,
        AuditTaskDeleteDialogComponent,
        AuditTaskPopupComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditTaskModule {}
