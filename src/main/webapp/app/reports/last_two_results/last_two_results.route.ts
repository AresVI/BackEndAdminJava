import {Routes} from '@angular/router';
import {UserRouteAccessService} from '../../shared/auth/user-route-access-service';
import {AuditProcessComponent} from './audit_process.component';
import {AuditTasksComponent} from './audit_tasks.component';

export const reportLastTwoResultsRoute: Routes = [
    {
        path: 'company/:id/compare_results',
        component: AuditProcessComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL', 'ROLE_AUDITOR_INTERNAL'],
            pageTitle: 'aresViApp.reports.last_two_results.index.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'company/:id/compare_results/:process_id',
        component: AuditTasksComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL', 'ROLE_AUDITOR_INTERNAL'],
            pageTitle: 'aresViApp.reports.last_two_results.process.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
