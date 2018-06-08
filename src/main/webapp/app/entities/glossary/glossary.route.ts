import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { GlossaryComponent } from './glossary.component';
import { GlossaryDetailComponent } from './glossary-detail.component';
import { GlossaryPopupComponent } from './glossary-dialog.component';
import { GlossaryDeletePopupComponent } from './glossary-delete-dialog.component';

@Injectable()
export class GlossaryResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const glossaryRoute: Routes = [
    {
        path: 'glossary',
        component: GlossaryComponent,
        resolve: {
            'pagingParams': GlossaryResolvePagingParams
        },
        data: {
            pageTitle: 'aresViApp.glossary.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'glossary/:id',
        component: GlossaryDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.glossary.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const glossaryPopupRoute: Routes = [
    {
        path: 'glossary-new',
        component: GlossaryPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.glossary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'glossary/:id/edit',
        component: GlossaryPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.glossary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'glossary/:id/delete',
        component: GlossaryDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.glossary.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
