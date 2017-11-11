import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CompanyContactPersonService,
    CompanyContactPersonPopupService,
    CompanyContactPersonComponent,
    CompanyContactPersonDetailComponent,
    CompanyContactPersonDialogComponent,
    CompanyContactPersonPopupComponent,
    CompanyContactPersonDetailPopupComponent,
    CompanyContactPersonDeletePopupComponent,
    CompanyContactPersonDeleteDialogComponent,
    company_contact_personRoute,
    company_contact_personPopupRoute,
    CompanyContactPersonResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...company_contact_personRoute,
    ...company_contact_personPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CompanyContactPersonComponent,
        CompanyContactPersonDetailComponent,
        CompanyContactPersonDialogComponent,
        CompanyContactPersonDeleteDialogComponent,
        CompanyContactPersonPopupComponent,
        CompanyContactPersonDeletePopupComponent,
        CompanyContactPersonDetailPopupComponent,
    ],
    entryComponents: [
        CompanyContactPersonComponent,
        CompanyContactPersonDialogComponent,
        CompanyContactPersonPopupComponent,
        CompanyContactPersonDetailPopupComponent,
        CompanyContactPersonDetailComponent,
        CompanyContactPersonDeleteDialogComponent,
        CompanyContactPersonDeletePopupComponent,
    ],
    providers: [
        CompanyContactPersonService,
        CompanyContactPersonPopupService,
        CompanyContactPersonResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCompanyContactPersonModule {}
