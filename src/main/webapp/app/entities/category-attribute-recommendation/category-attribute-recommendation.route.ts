import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CategoryAttributeRecommendationComponent } from './category-attribute-recommendation.component';
import { CategoryAttributeRecommendationDetailComponent } from './category-attribute-recommendation-detail.component';
import { CategoryAttributeRecommendationPopupComponent } from './category-attribute-recommendation-dialog.component';
import {
    CategoryAttributeRecommendationDeletePopupComponent
} from './category-attribute-recommendation-delete-dialog.component';

export const categoryAttributeRecommendationRoute: Routes = [
    {
        path: 'category-attribute-recommendation',
        component: CategoryAttributeRecommendationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-attribute-recommendation/:id',
        component: CategoryAttributeRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryAttributeRecommendationPopupRoute: Routes = [
    {
        path: 'category-attribute-recommendation-new',
        component: CategoryAttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attribute-recommendation/:id/edit',
        component: CategoryAttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-attribute-recommendation/:id/delete',
        component: CategoryAttributeRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
