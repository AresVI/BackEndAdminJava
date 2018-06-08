import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    GlossaryService,
    GlossaryPopupService,
    GlossaryComponent,
    GlossaryDetailComponent,
    GlossaryDialogComponent,
    GlossaryPopupComponent,
    GlossaryDeletePopupComponent,
    GlossaryDeleteDialogComponent,
    glossaryRoute,
    glossaryPopupRoute,
    GlossaryResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...glossaryRoute,
    ...glossaryPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        GlossaryComponent,
        GlossaryDetailComponent,
        GlossaryDialogComponent,
        GlossaryDeleteDialogComponent,
        GlossaryPopupComponent,
        GlossaryDeletePopupComponent,
    ],
    entryComponents: [
        GlossaryComponent,
        GlossaryDialogComponent,
        GlossaryPopupComponent,
        GlossaryDeleteDialogComponent,
        GlossaryDeletePopupComponent,
    ],
    providers: [
        GlossaryService,
        GlossaryPopupService,
        GlossaryResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViGlossaryModule {}
