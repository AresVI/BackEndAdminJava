import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    WeightingService,
    WeightingPopupService,
    WeightingComponent,
    WeightingDetailComponent,
    WeightingDialogComponent,
    WeightingPopupComponent,
    WeightingDeletePopupComponent,
    WeightingDeleteDialogComponent,
    weightingRoute,
    weightingPopupRoute,
    WeightingResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...weightingRoute,
    ...weightingPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        WeightingComponent,
        WeightingDetailComponent,
        WeightingDialogComponent,
        WeightingDeleteDialogComponent,
        WeightingPopupComponent,
        WeightingDeletePopupComponent,
    ],
    entryComponents: [
        WeightingComponent,
        WeightingDialogComponent,
        WeightingPopupComponent,
        WeightingDeleteDialogComponent,
        WeightingDeletePopupComponent,
    ],
    providers: [
        WeightingService,
        WeightingPopupService,
        WeightingResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViWeightingModule {}
