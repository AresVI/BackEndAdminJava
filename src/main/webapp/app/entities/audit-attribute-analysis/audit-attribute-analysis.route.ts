import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AuditAttributeAnalysisComponent } from './audit-attribute-analysis.component';
import { AuditAttributeAnalysisDetailComponent } from './audit-attribute-analysis-detail.component';
import { AuditAttributeAnalysisPopupComponent } from './audit-attribute-analysis-dialog.component';
import { AuditAttributeAnalysisDeletePopupComponent } from './audit-attribute-analysis-delete-dialog.component';

export const auditAttributeAnalysisRoute: Routes = [
    {
        path: 'audit-attribute-analysis',
        component: AuditAttributeAnalysisComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditAttributeAnalysis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'audit-attribute-analysis/:id',
        component: AuditAttributeAnalysisDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditAttributeAnalysis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const auditAttributeAnalysisPopupRoute: Routes = [
    {
        path: 'audit-attribute-analysis-new',
        component: AuditAttributeAnalysisPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditAttributeAnalysis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-attribute-analysis/:id/edit',
        component: AuditAttributeAnalysisPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditAttributeAnalysis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'audit-attribute-analysis/:id/delete',
        component: AuditAttributeAnalysisDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.auditAttributeAnalysis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
