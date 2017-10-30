import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CancellationTraceabilityAuditComponent } from './cancellation-traceability-audit.component';
import { CancellationTraceabilityAuditDetailComponent } from './cancellation-traceability-audit-detail.component';
import { CancellationTraceabilityAuditPopupComponent } from './cancellation-traceability-audit-dialog.component';
import { CancellationTraceabilityAuditDeletePopupComponent } from './cancellation-traceability-audit-delete-dialog.component';

@Injectable()
export class CancellationTraceabilityAuditResolvePagingParams implements Resolve<any> {

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

export const cancellationTraceabilityAuditRoute: Routes = [
    {
        path: 'cancellation-traceability-audit',
        component: CancellationTraceabilityAuditComponent,
        resolve: {
            'pagingParams': CancellationTraceabilityAuditResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.cancellationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'cancellation-traceability-audit/:id',
        component: CancellationTraceabilityAuditDetailComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.cancellationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cancellationTraceabilityAuditPopupRoute: Routes = [
    {
        path: 'traceability-audit/:traceability_audit/cancellation-new',
        component: CancellationTraceabilityAuditPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.cancellationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cancellation-traceability-audit/:id/edit',
        component: CancellationTraceabilityAuditPopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.cancellationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'cancellation-traceability-audit/:id/delete',
        component: CancellationTraceabilityAuditDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER', 'ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.cancellationTraceabilityAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
