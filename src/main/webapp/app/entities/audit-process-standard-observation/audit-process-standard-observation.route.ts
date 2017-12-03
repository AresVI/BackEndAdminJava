import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditProcessStandardObservationComponent } from './audit-process-standard-observation.component';
import { AuditProcessStandardObservationDetailComponent } from './audit-process-standard-observation-detail.component';
import { AuditProcessStandardObservationPopupComponent } from './audit-process-standard-observation-dialog.component';
import {
    AuditProcessStandardObservationDeletePopupComponent
} from './audit-process-standard-observation-delete-dialog.component';

@Injectable()
export class AuditProcessStandardObservationResolvePagingParams implements Resolve<any> {

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

export const auditProcessStandardObservationRoute: Routes = [
    {
        path: 'audit-process-standard-observation',
        component: AuditProcessStandardObservationComponent,
        resolve: {
            'pagingParams': AuditProcessStandardObservationResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-process-standard-observation/:id',
        component: AuditProcessStandardObservationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditProcessStandardObservationPopupRoute: Routes = [
    {
        path: 'audit-process-standard-observation-new',
        component: AuditProcessStandardObservationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process-standard-observation/:id/edit',
        component: AuditProcessStandardObservationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process-standard-observation/:id/delete',
        component: AuditProcessStandardObservationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessStandardObservation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
