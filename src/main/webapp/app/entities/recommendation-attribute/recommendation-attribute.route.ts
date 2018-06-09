import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RecommendationAttributeComponent } from './recommendation-attribute.component';
import { RecommendationAttributeDetailComponent } from './recommendation-attribute-detail.component';
import { RecommendationAttributePopupComponent } from './recommendation-attribute-dialog.component';
import { RecommendationAttributeDeletePopupComponent } from './recommendation-attribute-delete-dialog.component';

export const recommendationAttributeRoute: Routes = [
    {
        path: 'recommendation-attribute',
        component: RecommendationAttributeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'recommendation-attribute/:id',
        component: RecommendationAttributeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttribute.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recommendationAttributePopupRoute: Routes = [
    {
        path: 'recommendation-attribute-new',
        component: RecommendationAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation-attribute/:id/edit',
        component: RecommendationAttributePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recommendation-attribute/:id/delete',
        component: RecommendationAttributeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aresViApp.recommendationAttribute.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
