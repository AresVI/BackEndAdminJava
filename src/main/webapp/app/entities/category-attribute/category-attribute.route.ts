import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CategoryAttributeComponent } from './category-attribute.component';
import { CategoryAttributeDetailComponent } from './category-attribute-detail.component';
import { CategoryAttributePopupComponent } from './category-attribute-dialog.component';
import { CategoryAttributeDeletePopupComponent } from './category-attribute-delete-dialog.component';

@Injectable()
export class CategoryAttributeResolvePagingParams implements Resolve<any> {

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

export const categoryAttributeRoute: Routes = [
    {
        path: 'category-attribute',
        component: CategoryAttributeComponent,
        resolve: {
            'pagingParams': CategoryAttributeResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-attribute/:id',
        component: CategoryAttributeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryAttributePopupRoute: Routes = [
    {
        path: 'category-attribute-new',
        component: CategoryAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attribute/:id/edit',
        component: CategoryAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attribute/:id/delete',
        component: CategoryAttributeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
