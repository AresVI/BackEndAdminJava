import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    FaqsService,
    FaqsPopupService,
    FaqsComponent,
    FaqsDetailComponent,
    FaqsDialogComponent,
    FaqsPopupComponent,
    FaqsDeletePopupComponent,
    FaqsDeleteDialogComponent,
    faqsRoute,
    faqsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...faqsRoute,
    ...faqsPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        FaqsComponent,
        FaqsDetailComponent,
        FaqsDialogComponent,
        FaqsDeleteDialogComponent,
        FaqsPopupComponent,
        FaqsDeletePopupComponent,
    ],
    entryComponents: [
        FaqsComponent,
        FaqsDialogComponent,
        FaqsPopupComponent,
        FaqsDeleteDialogComponent,
        FaqsDeletePopupComponent,
    ],
    providers: [
        FaqsService,
        FaqsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFaqsModule {}
