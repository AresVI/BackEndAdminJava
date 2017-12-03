import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditTaskStandardObservationComponent } from './audit-task-standard-observation.component';
import { AuditTaskStandardObservationDetailComponent } from './audit-task-standard-observation-detail.component';
import { AuditTaskStandardObservationPopupComponent } from './audit-task-standard-observation-dialog.component';
import {
    AuditTaskStandardObservationDeletePopupComponent
} from './audit-task-standard-observation-delete-dialog.component';

@Injectable()
export class AuditTaskStandardObservationResolvePagingParams implements Resolve<any> {

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

export const auditTaskStandardObservationRoute: Routes = [
    {
        path: 'audit-task-standard-observation',
        component: AuditTaskStandardObservationComponent,
        resolve: {
            'pagingParams': AuditTaskStandardObservationResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-task-standard-observation/:id',
        component: AuditTaskStandardObservationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditTaskStandardObservationPopupRoute: Routes = [
    {
        path: 'audit-task-standard-observation-new',
        component: AuditTaskStandardObservationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task-standard-observation/:id/edit',
        component: AuditTaskStandardObservationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task-standard-observation/:id/delete',
        component: AuditTaskStandardObservationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
