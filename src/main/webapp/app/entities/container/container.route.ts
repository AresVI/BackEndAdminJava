import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ContainerComponent } from './container.component';
import { ContainerDetailComponent } from './container-detail.component';
import { ContainerPopupComponent } from './container-dialog.component';
import { ContainerDeletePopupComponent } from './container-delete-dialog.component';

@Injectable()
export class ContainerResolvePagingParams implements Resolve<any> {

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

export const containerRoute: Routes = [
    {
        path: 'container',
        component: ContainerComponent,
        resolve: {
            'pagingParams': ContainerResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.container.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'container/:id',
        component: ContainerDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.container.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const containerPopupRoute: Routes = [
    {
        path: 'container-new',
        component: ContainerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.container.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'container/:id/edit',
        component: ContainerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.container.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'container/:id/delete',
        component: ContainerDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.container.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
