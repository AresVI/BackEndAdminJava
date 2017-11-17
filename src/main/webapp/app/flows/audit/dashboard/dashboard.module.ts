import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AresViSharedModule } from '../../../shared';
import {flowAuditDasboardRoute} from './dashboard.route';
import {DashBoardComponent} from './dashboard.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';

const ENTITY_STATES = [
    ...flowAuditDasboardRoute
];

@NgModule({
    imports: [
        AresViSharedModule,
        Ng2SearchPipeModule,
        RouterModule.forRoot(ENTITY_STATES , { useHash: true })
    ],
    declarations: [
        DashBoardComponent
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AresViFlowAuditDashboardModule {}
