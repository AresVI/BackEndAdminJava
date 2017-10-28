import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditTaskRecommendationComponent } from './audit-task-recommendation.component';
import { AuditTaskRecommendationDetailComponent } from './audit-task-recommendation-detail.component';
import { AuditTaskRecommendationPopupComponent } from './audit-task-recommendation-dialog.component';
import { AuditTaskRecommendationDeletePopupComponent } from './audit-task-recommendation-delete-dialog.component';

export const auditTaskRecommendationRoute: Routes = [
    {
        path: 'audit-task-recommendation',
        component: AuditTaskRecommendationComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-task-recommendation/:id',
        component: AuditTaskRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR'],
            pageTitle: 'aresViApp.auditTaskRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditTaskRecommendationPopupRoute: Routes = [
    {
        path: 'audit-task-recommendation-new',
        component: AuditTaskRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task-recommendation/:id/edit',
        component: AuditTaskRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR'],
            pageTitle: 'aresViApp.auditTaskRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-task-recommendation/:id/delete',
        component: AuditTaskRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditTaskRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
