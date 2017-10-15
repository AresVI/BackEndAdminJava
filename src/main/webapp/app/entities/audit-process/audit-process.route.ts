import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditProcessComponent } from './audit-process.component';
import { AuditProcessDetailComponent } from './audit-process-detail.component';
import { AuditProcessPopupComponent } from './audit-process-dialog.component';
import { AuditProcessDeletePopupComponent } from './audit-process-delete-dialog.component';

@Injectable()
export class AuditProcessResolvePagingParams implements Resolve<any> {

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

export const auditProcessRoute: Routes = [
    {
        path: 'audit-process',
        component: AuditProcessComponent,
        resolve: {
            'pagingParams': AuditProcessResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-process/:id',
        component: AuditProcessDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcess.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditProcessPopupRoute: Routes = [
    {
        path: 'audit-process-new',
        component: AuditProcessPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process/:id/edit',
        component: AuditProcessPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process/:id/delete',
        component: AuditProcessDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcess.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
