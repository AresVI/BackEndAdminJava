import { Route } from '@angular/router';

import { JhiDocsComponent } from './docs.component';

export const docsRoute: Route = {
    path: 'docs',
    component: JhiDocsComponent,
    data: {
        authorities: ['ROLE_ADMINISTRATOR'],
        pageTitle: 'global.menu.admin.apidocs'
    }
};
