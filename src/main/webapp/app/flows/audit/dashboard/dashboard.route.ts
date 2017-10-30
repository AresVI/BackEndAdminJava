import { Routes } from '@angular/router';
import {DashBoardComponent} from './dashboard.component';
import { UserRouteAccessService } from '../../../shared';

export const flowAuditDasboardRoute: Routes = [
    {
        path: 'process/audit/dashboard',
        component: DashBoardComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.flow-audit.dashboard.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
