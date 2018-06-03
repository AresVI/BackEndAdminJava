import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TraceAuditComponent } from './trace-audit.component';
import { TraceAuditDetailComponent } from './trace-audit-detail.component';
import { TraceAuditPopupComponent } from './trace-audit-dialog.component';
import { TraceAuditDeletePopupComponent } from './trace-audit-delete-dialog.component';

export const traceAuditRoute: Routes = [
    {
        path: 'traceability-audit/:traceabilityAuditId/trace-audit',
        component: TraceAuditComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.traceAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'traceability-audit/:traceabilityAuditId/trace-audit/:id',
        component: TraceAuditDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.traceAudit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const traceAuditPopupRoute: Routes = [
    {
        path: 'traceability-audit/:traceabilityAuditId/trace-audit-new',
        component: TraceAuditPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.traceAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'traceability-audit/:traceabilityAuditId/trace-audit/:id/edit',
        component: TraceAuditPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.traceAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'traceability-audit/:traceabilityAuditId/trace-audit/:id/delete',
        component: TraceAuditDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.traceAudit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
