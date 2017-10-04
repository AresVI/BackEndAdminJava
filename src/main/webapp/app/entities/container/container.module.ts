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

const ENTITY_STATES = [
    ...containerRoute,
    ...containerPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
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
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViContainerModule {}
