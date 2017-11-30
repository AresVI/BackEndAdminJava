import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../shared';
import {
    CompanyAddressService,
    CompanyAddressPopupService,
    CompanyAddressComponent,
    CompanyAddressDetailComponent,
    CompanyAddressDialogComponent,
    CompanyAddressPopupComponent,
    CompanyAddressDeletePopupComponent,
    CompanyAddressDeleteDialogComponent,
    companyAddressRoute,
    companyAddressPopupRoute,
    CompanyAddressResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...companyAddressRoute,
    ...companyAddressPopupRoute,
];

@NgModule({
    imports: [
        AresViSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        CompanyAddressComponent,
        CompanyAddressDetailComponent,
        CompanyAddressDialogComponent,
        CompanyAddressDeleteDialogComponent,
        CompanyAddressPopupComponent,
        CompanyAddressDeletePopupComponent,
    ],
    entryComponents: [
        CompanyAddressComponent,
        CompanyAddressDialogComponent,
        CompanyAddressPopupComponent,
        CompanyAddressDeleteDialogComponent,
        CompanyAddressDeletePopupComponent,
    ],
    providers: [
        CompanyAddressService,
        CompanyAddressPopupService,
        CompanyAddressResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViCompanyAddressModule {}
