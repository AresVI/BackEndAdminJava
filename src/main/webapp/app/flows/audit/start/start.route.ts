import { Routes } from '@angular/router';
import {StartComponent} from "./start.component";


export const flowAuditStartRoute: Routes = [
    {
        path: 'process/audit/start',
        component: StartComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.flow-audit.start.home.title'
        }
    }
];
