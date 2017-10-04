import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CategoryAttRecommendationService,
    CategoryAttRecommendationPopupService,
    CategoryAttRecommendationComponent,
    CategoryAttRecommendationDetailComponent,
    CategoryAttRecommendationDialogComponent,
    CategoryAttRecommendationPopupComponent,
    CategoryAttRecommendationDeletePopupComponent,
    CategoryAttRecommendationDeleteDialogComponent,
    categoryAttRecommendationRoute,
    categoryAttRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...categoryAttRecommendationRoute,
    ...categoryAttRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CategoryAttRecommendationComponent,
        CategoryAttRecommendationDetailComponent,
        CategoryAttRecommendationDialogComponent,
        CategoryAttRecommendationDeleteDialogComponent,
        CategoryAttRecommendationPopupComponent,
        CategoryAttRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        CategoryAttRecommendationComponent,
        CategoryAttRecommendationDialogComponent,
        CategoryAttRecommendationPopupComponent,
        CategoryAttRecommendationDeleteDialogComponent,
        CategoryAttRecommendationDeletePopupComponent,
    ],
    providers: [
        CategoryAttRecommendationService,
        CategoryAttRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCategoryAttRecommendationModule {}
