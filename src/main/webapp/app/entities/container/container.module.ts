import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    ContainerService,
    ContainerPopupService,
    ContainerComponent,
    ContainerDetailComponent,
    ContainerDialogComponent,
    ContainerPopupComponent,
    ContainerDeletePopupComponent,
    ContainerDeleteDialogComponent,
    containerRoute,
    containerPopupRoute,
    ContainerResolvePagingParams,
} from './';
import {AresViAuditTaskModule} from '../audit-task/audit-task.module';

const ENTITY_STATES = [
    ...containerRoute,
    ...containerPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        AresViAuditTaskModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ContainerComponent,
        ContainerDetailComponent,
        ContainerDialogComponent,
        ContainerDeleteDialogComponent,
        ContainerPopupComponent,
        ContainerDeletePopupComponent,
    ],
    entryComponents: [
        ContainerComponent,
        ContainerDialogComponent,
        ContainerPopupComponent,
        ContainerDeleteDialogComponent,
        ContainerDeletePopupComponent,
    ],
    providers: [
        ContainerService,
        ContainerPopupService,
        ContainerResolvePagingParams,
    ],
    exports: [
        ContainerComponent,
        ContainerDialogComponent,
        ContainerPopupComponent,
        ContainerDeleteDialogComponent,
        ContainerDeletePopupComponent,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViContainerModule {}
