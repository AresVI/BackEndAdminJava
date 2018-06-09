import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    RecommendationAttributeService,
    RecommendationAttributePopupService,
    RecommendationAttributeComponent,
    RecommendationAttributeDetailComponent,
    RecommendationAttributeDialogComponent,
    RecommendationAttributePopupComponent,
    RecommendationAttributeDeletePopupComponent,
    RecommendationAttributeDeleteDialogComponent,
    recommendationAttributeRoute,
    recommendationAttributePopupRoute,
} from './';

const ENTITY_STATES = [
    ...recommendationAttributeRoute,
    ...recommendationAttributePopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RecommendationAttributeComponent,
        RecommendationAttributeDetailComponent,
        RecommendationAttributeDialogComponent,
        RecommendationAttributeDeleteDialogComponent,
        RecommendationAttributePopupComponent,
        RecommendationAttributeDeletePopupComponent,
    ],
    entryComponents: [
        RecommendationAttributeComponent,
        RecommendationAttributeDialogComponent,
        RecommendationAttributePopupComponent,
        RecommendationAttributeDeleteDialogComponent,
        RecommendationAttributeDeletePopupComponent,
    ],
    providers: [
        RecommendationAttributeService,
        RecommendationAttributePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViRecommendationAttributeModule {}
