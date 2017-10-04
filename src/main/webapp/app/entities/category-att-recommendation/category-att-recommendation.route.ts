import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CategoryAttRecommendationComponent } from './category-att-recommendation.component';
import { CategoryAttRecommendationDetailComponent } from './category-att-recommendation-detail.component';
import { CategoryAttRecommendationPopupComponent } from './category-att-recommendation-dialog.component';
import { CategoryAttRecommendationDeletePopupComponent } from './category-att-recommendation-delete-dialog.component';

export const categoryAttRecommendationRoute: Routes = [
    {
        path: 'category-att-recommendation',
        component: CategoryAttRecommendationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'category-att-recommendation/:id',
        component: CategoryAttRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categoryAttRecommendationPopupRoute: Routes = [
    {
        path: 'category-att-recommendation-new',
        component: CategoryAttRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-att-recommendation/:id/edit',
        component: CategoryAttRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'category-att-recommendation/:id/delete',
        component: CategoryAttRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.categoryAttRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
