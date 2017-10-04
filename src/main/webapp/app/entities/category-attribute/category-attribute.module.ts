import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CategoryAttributeService,
    CategoryAttributePopupService,
    CategoryAttributeComponent,
    CategoryAttributeDetailComponent,
    CategoryAttributeDialogComponent,
    CategoryAttributePopupComponent,
    CategoryAttributeDeletePopupComponent,
    CategoryAttributeDeleteDialogComponent,
    categoryAttributeRoute,
    categoryAttributePopupRoute,
    CategoryAttributeResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...categoryAttributeRoute,
    ...categoryAttributePopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CategoryAttributeComponent,
        CategoryAttributeDetailComponent,
        CategoryAttributeDialogComponent,
        CategoryAttributeDeleteDialogComponent,
        CategoryAttributePopupComponent,
        CategoryAttributeDeletePopupComponent,
    ],
    entryComponents: [
        CategoryAttributeComponent,
        CategoryAttributeDialogComponent,
        CategoryAttributePopupComponent,
        CategoryAttributeDeleteDialogComponent,
        CategoryAttributeDeletePopupComponent,
    ],
    providers: [
        CategoryAttributeService,
        CategoryAttributePopupService,
        CategoryAttributeResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCategoryAttributeModule {}
