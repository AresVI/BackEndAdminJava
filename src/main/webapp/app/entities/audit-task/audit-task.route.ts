import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditTaskComponent } from './audit-task.component';
import { AuditTaskDetailComponent } from './audit-task-detail.component';
import { AuditTaskPopupComponent } from './audit-task-dialog.component';
import { AuditTaskDeletePopupComponent } from './audit-task-delete-dialog.component';

@Injectable()
export class AuditTaskResolvePagingParams implements Resolve<any> {

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

export const auditTaskRoute: Routes = [
    {
        path: 'audit-task',
        component: AuditTaskComponent,
        resolve: {
            'pagingParams': AuditTaskResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-task/:id',
        component: AuditTaskDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditTask.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditTaskPopupRoute: Routes = [
    {
        path: 'audit-task-new',
        component: AuditTaskPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditTask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task/:id/edit',
        component: AuditTaskPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditTask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task/:id/delete',
        component: AuditTaskDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditTask.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
