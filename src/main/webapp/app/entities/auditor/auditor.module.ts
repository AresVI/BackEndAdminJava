import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import { AresViAdminModule } from '../../admin/admin.module';
import {
    AuditorService,
    AuditorPopupService,
    AuditorComponent,
    AuditorDetailComponent,
    AuditorDialogComponent,
    AuditorPopupComponent,
    AuditorDeletePopupComponent,
    AuditorDeleteDialogComponent,
    auditorRoute,
    auditorPopupRoute,
    AuditorResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...auditorRoute,
    ...auditorPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        AresViAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AuditorComponent,
        AuditorDetailComponent,
        AuditorDialogComponent,
        AuditorDeleteDialogComponent,
        AuditorPopupComponent,
        AuditorDeletePopupComponent,
    ],
    entryComponents: [
        AuditorComponent,
        AuditorDialogComponent,
        AuditorPopupComponent,
        AuditorDeleteDialogComponent,
        AuditorDeletePopupComponent,
    ],
    providers: [
        AuditorService,
        AuditorPopupService,
        AuditorResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViAuditorModule {}
