import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditorComponent } from './auditor.component';
import { AuditorDetailComponent } from './auditor-detail.component';
import { AuditorPopupComponent } from './auditor-dialog.component';
import { AuditorDeletePopupComponent } from './auditor-delete-dialog.component';

@Injectable()
export class AuditorResolvePagingParams implements Resolve<any> {

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

export const auditorRoute: Routes = [
    {
        path: 'auditor',
        component: AuditorComponent,
        resolve: {
            'pagingParams': AuditorResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.auditor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'auditor/:id',
        component: AuditorDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.auditor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditorPopupRoute: Routes = [
    {
        path: 'auditor-new',
        component: AuditorPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.auditor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'auditor/:id/edit',
        component: AuditorPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.auditor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'auditor/:id/delete',
        component: AuditorDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.auditor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
