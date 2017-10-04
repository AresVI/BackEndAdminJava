import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    AttributeRecommendationService,
    AttributeRecommendationPopupService,
    AttributeRecommendationComponent,
    AttributeRecommendationDetailComponent,
    AttributeRecommendationDialogComponent,
    AttributeRecommendationPopupComponent,
    AttributeRecommendationDeletePopupComponent,
    AttributeRecommendationDeleteDialogComponent,
    attributeRecommendationRoute,
    attributeRecommendationPopupRoute,
} from './';

const ENTITY_STATES = [
    ...attributeRecommendationRoute,
    ...attributeRecommendationPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AttributeRecommendationComponent,
        AttributeRecommendationDetailComponent,
        AttributeRecommendationDialogComponent,
        AttributeRecommendationDeleteDialogComponent,
        AttributeRecommendationPopupComponent,
        AttributeRecommendationDeletePopupComponent,
    ],
    entryComponents: [
        AttributeRecommendationComponent,
        AttributeRecommendationDialogComponent,
        AttributeRecommendationPopupComponent,
        AttributeRecommendationDeleteDialogComponent,
        AttributeRecommendationDeletePopupComponent,
    ],
    providers: [
        AttributeRecommendationService,
        AttributeRecommendationPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAttributeRecommendationModule {}
