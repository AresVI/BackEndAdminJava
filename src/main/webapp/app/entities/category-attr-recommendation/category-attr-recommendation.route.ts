import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';

import { CategoryAttrRecommendationComponent } from './category-attr-recommendation.component';
import { CategoryAttrRecommendationDetailComponent } from './category-attr-recommendation-detail.component';
import { CategoryAttrRecommendationPopupComponent } from './category-attr-recommendation-dialog.component';
import { CategoryAttrRecommendationDeletePopupComponent } from './category-attr-recommendation-delete-dialog.component';

export const categoryAttrRecommendationRoute: Routes = [
    {
        path: 'category-attr-recommendation',
        component: CategoryAttrRecommendationComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.categoryAttrRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-attr-recommendation/:id',
        component: CategoryAttrRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.categoryAttrRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryAttrRecommendationPopupRoute: Routes = [
    {
        path: 'category-attr-recommendation-new',
        component: CategoryAttrRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.categoryAttrRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attr-recommendation/:id/edit',
        component: CategoryAttrRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.categoryAttrRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attr-recommendation/:id/delete',
        component: CategoryAttrRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.categoryAttrRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
