import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RecommendationComponent } from './recommendation.component';
import { RecommendationDetailComponent } from './recommendation-detail.component';
import { RecommendationPopupComponent } from './recommendation-dialog.component';
import { RecommendationDeletePopupComponent } from './recommendation-delete-dialog.component';

export const recommendationRoute: Routes = [
    {
        path: 'recommendation',
        component: RecommendationComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.recommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'recommendation/:id',
        component: RecommendationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR'],
            pageTitle: 'aresViApp.recommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recommendationPopupRoute: Routes = [
    {
        path: 'recommendation-new',
        component: RecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.recommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation/:id/edit',
        component: RecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_AUDITOR'],
            pageTitle: 'aresViApp.recommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation/:id/delete',
        component: RecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.recommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
