import { Routes } from '@angular/router';
import {DashBoardComponent} from './dashboard.component';

export const flowAuditDasboardRoute: Routes = [
    {
        path: 'process/audit/dashboard',
        component: DashBoardComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.flow-audit.dashboard.home.title'
        }
    }
];
