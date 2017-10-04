import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    RecommendationService,
    RecommendationPopupService,
    RecommendationComponent,
    RecommendationDetailComponent,
    RecommendationDialogComponent,
    RecommendationPopupComponent,
    RecommendationDeletePopupComponent,
    RecommendationDeleteDialogComponent,
    recommendationRoute,
    recommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...recommendationRoute,
    ...recommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RecommendationComponent,
        RecommendationDetailComponent,
        RecommendationDialogComponent,
        RecommendationDeleteDialogComponent,
        RecommendationPopupComponent,
        RecommendationDeletePopupComponent,
    ],
    entryComponents: [
        RecommendationComponent,
        RecommendationDialogComponent,
        RecommendationPopupComponent,
        RecommendationDeleteDialogComponent,
        RecommendationDeletePopupComponent,
    ],
    providers: [
        RecommendationService,
        RecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViRecommendationModule {}
