import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CancelationTraceabilityAuditComponent } from './cancelation-traceability-audit.component';
import { CancelationTraceabilityAuditDetailComponent } from './cancelation-traceability-audit-detail.component';
import { CancelationTraceabilityAuditPopupComponent } from './cancelation-traceability-audit-dialog.component';
import { CancelationTraceabilityAuditDeletePopupComponent } from './cancelation-traceability-audit-delete-dialog.component';

@Injectable()
export class CancelationTraceabilityAuditResolvePagingParams implements Resolve<any> {

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

export const cancelationTraceabilityAuditRoute: Routes = [
    {
        path: 'cancelation-traceability-audit',
        component: CancelationTraceabilityAuditComponent,
        resolve: {
            'pagingParams': CancelationTraceabilityAuditResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.cancelationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'cancelation-traceability-audit/:id',
        component: CancelationTraceabilityAuditDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.cancelationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cancelationTraceabilityAuditPopupRoute: Routes = [
    {
        path: 'cancelation-traceability-audit-new',
        component: CancelationTraceabilityAuditPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.cancelationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cancelation-traceability-audit/:id/edit',
        component: CancelationTraceabilityAuditPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.cancelationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cancelation-traceability-audit/:id/delete',
        component: CancelationTraceabilityAuditDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.cancelationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
