import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CategoryAttributeRecommendationService,
    CategoryAttributeRecommendationPopupService,
    CategoryAttributeRecommendationComponent,
    CategoryAttributeRecommendationDetailComponent,
    CategoryAttributeRecommendationDialogComponent,
    CategoryAttributeRecommendationPopupComponent,
    CategoryAttributeRecommendationDeletePopupComponent,
    CategoryAttributeRecommendationDeleteDialogComponent,
    categoryAttributeRecommendationRoute,
    categoryAttributeRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...categoryAttributeRecommendationRoute,
    ...categoryAttributeRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CategoryAttributeRecommendationComponent,
        CategoryAttributeRecommendationDetailComponent,
        CategoryAttributeRecommendationDialogComponent,
        CategoryAttributeRecommendationDeleteDialogComponent,
        CategoryAttributeRecommendationPopupComponent,
        CategoryAttributeRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        CategoryAttributeRecommendationComponent,
        CategoryAttributeRecommendationDialogComponent,
        CategoryAttributeRecommendationPopupComponent,
        CategoryAttributeRecommendationDeleteDialogComponent,
        CategoryAttributeRecommendationDeletePopupComponent,
    ],
    providers: [
        CategoryAttributeRecommendationService,
        CategoryAttributeRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCategoryAttributeRecommendationModule {}
