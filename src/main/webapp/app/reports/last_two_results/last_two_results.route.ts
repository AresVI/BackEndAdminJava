import {Routes} from '@angular/router';
import {UserRouteAccessService} from '../../shared/auth/user-route-access-service';
import {AuditProcessComponent} from './audit_process.component';

export const reportLastTwoResultsRoute: Routes = [
    {
        path: 'company/:id/compare_results',
        component: AuditProcessComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL', 'ROLE_AUDITOR_INTERNAL'],
            pageTitle: 'aresViApp.company.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
