import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CategoryAttrRecommendationService,
    CategoryAttrRecommendationPopupService,
    CategoryAttrRecommendationComponent,
    CategoryAttrRecommendationDetailComponent,
    CategoryAttrRecommendationDialogComponent,
    CategoryAttrRecommendationPopupComponent,
    CategoryAttrRecommendationDeletePopupComponent,
    CategoryAttrRecommendationDeleteDialogComponent,
    categoryAttrRecommendationRoute,
    categoryAttrRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...categoryAttrRecommendationRoute,
    ...categoryAttrRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CategoryAttrRecommendationComponent,
        CategoryAttrRecommendationDetailComponent,
        CategoryAttrRecommendationDialogComponent,
        CategoryAttrRecommendationDeleteDialogComponent,
        CategoryAttrRecommendationPopupComponent,
        CategoryAttrRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        CategoryAttrRecommendationComponent,
        CategoryAttrRecommendationDialogComponent,
        CategoryAttrRecommendationPopupComponent,
        CategoryAttrRecommendationDeleteDialogComponent,
        CategoryAttrRecommendationDeletePopupComponent,
    ],
    providers: [
        CategoryAttrRecommendationService,
        CategoryAttrRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCategoryAttrRecommendationModule {}
