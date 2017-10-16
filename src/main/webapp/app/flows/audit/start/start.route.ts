import { Routes } from '@angular/router';
import {StartComponent} from './start.component';
import {UserRouteAccessService} from '../../../shared/auth/user-route-access-service';
import {CompanyContactPersonAuditStartPopupComponent} from './company-contact-person-dialog.component';

export const flowAuditStartRoute: Routes = [
    {
        path: 'process/audit/start',
        component: StartComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.flow-audit.start.home.title'
        }
    },
    {
        path: 'process/audit/start/company/:company_id/company-contact-person-new',
        component: CompanyContactPersonAuditStartPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
