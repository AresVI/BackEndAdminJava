import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RecommendationAttributeRecommendationComponent } from './recommendation-attribute-recommendation.component';
import { RecommendationAttributeRecommendationDetailComponent } from './recommendation-attribute-recommendation-detail.component';
import { RecommendationAttributeRecommendationPopupComponent } from './recommendation-attribute-recommendation-dialog.component';
import {
    RecommendationAttributeRecommendationDeletePopupComponent
} from './recommendation-attribute-recommendation-delete-dialog.component';

export const recommendationAttributeRecommendationRoute: Routes = [
    {
        path: 'recommendation-attribute-recommendation',
        component: RecommendationAttributeRecommendationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'recommendation-attribute-recommendation/:id',
        component: RecommendationAttributeRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recommendationAttributeRecommendationPopupRoute: Routes = [
    {
        path: 'recommendation-attribute-recommendation-new',
        component: RecommendationAttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation-attribute-recommendation/:id/edit',
        component: RecommendationAttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation-attribute-recommendation/:id/delete',
        component: RecommendationAttributeRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
