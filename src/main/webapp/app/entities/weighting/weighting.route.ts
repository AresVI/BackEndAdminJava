import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { WeightingComponent } from './weighting.component';
import { WeightingDetailComponent } from './weighting-detail.component';
import { WeightingPopupComponent } from './weighting-dialog.component';
import { WeightingDeletePopupComponent } from './weighting-delete-dialog.component';

@Injectable()
export class WeightingResolvePagingParams implements Resolve<any> {

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

export const weightingRoute: Routes = [
    {
        path: 'weighting',
        component: WeightingComponent,
        resolve: {
            'pagingParams': WeightingResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.weighting.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'weighting/:id',
        component: WeightingDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.weighting.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const weightingPopupRoute: Routes = [
    {
        path: 'weighting-new',
        component: WeightingPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.weighting.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'weighting/:id/edit',
        component: WeightingPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.weighting.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'weighting/:id/delete',
        component: WeightingDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.weighting.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
