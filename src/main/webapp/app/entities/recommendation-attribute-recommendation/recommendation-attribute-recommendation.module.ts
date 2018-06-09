import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    RecommendationAttributeRecommendationService,
    RecommendationAttributeRecommendationPopupService,
    RecommendationAttributeRecommendationComponent,
    RecommendationAttributeRecommendationDetailComponent,
    RecommendationAttributeRecommendationDialogComponent,
    RecommendationAttributeRecommendationPopupComponent,
    RecommendationAttributeRecommendationDeletePopupComponent,
    RecommendationAttributeRecommendationDeleteDialogComponent,
    recommendationAttributeRecommendationRoute,
    recommendationAttributeRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...recommendationAttributeRecommendationRoute,
    ...recommendationAttributeRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RecommendationAttributeRecommendationComponent,
        RecommendationAttributeRecommendationDetailComponent,
        RecommendationAttributeRecommendationDialogComponent,
        RecommendationAttributeRecommendationDeleteDialogComponent,
        RecommendationAttributeRecommendationPopupComponent,
        RecommendationAttributeRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        RecommendationAttributeRecommendationComponent,
        RecommendationAttributeRecommendationDialogComponent,
        RecommendationAttributeRecommendationPopupComponent,
        RecommendationAttributeRecommendationDeleteDialogComponent,
        RecommendationAttributeRecommendationDeletePopupComponent,
    ],
    providers: [
        RecommendationAttributeRecommendationService,
        RecommendationAttributeRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViRecommendationAttributeRecommendationModule {}
