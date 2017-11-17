import { Route } from '@angular/router';

import { AuditsComponent } from './audits.component';

export const auditsRoute: Route = {
    path: 'audits',
    component: AuditsComponent,
    data: {
        authorities: ['ROLE_ADMINISTRATOR'],
        pageTitle: 'audits.title'
    }
};
