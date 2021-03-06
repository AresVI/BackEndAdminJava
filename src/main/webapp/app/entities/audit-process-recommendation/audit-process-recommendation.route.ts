import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';

import { AuditProcessRecommendationComponent } from './audit-process-recommendation.component';
import { AuditProcessRecommendationDetailComponent } from './audit-process-recommendation-detail.component';
import { AuditProcessRecommendationPopupComponent } from './audit-process-recommendation-dialog.component';
import { AuditProcessRecommendationDeletePopupComponent } from './audit-process-recommendation-delete-dialog.component';

export const auditProcessRecommendationRoute: Routes = [
    {
        path: 'audit-process-recommendation',
        component: AuditProcessRecommendationComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-process-recommendation/:id',
        component: AuditProcessRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.auditProcessRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditProcessRecommendationPopupRoute: Routes = [
    {
        path: 'audit-process-recommendation-new',
        component: AuditProcessRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process-recommendation/:id/edit',
        component: AuditProcessRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.auditProcessRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-process-recommendation/:id/delete',
        component: AuditProcessRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.auditProcessRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
