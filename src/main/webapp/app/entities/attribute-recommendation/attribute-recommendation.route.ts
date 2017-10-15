import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AttributeRecommendationComponent } from './attribute-recommendation.component';
import { AttributeRecommendationDetailComponent } from './attribute-recommendation-detail.component';
import { AttributeRecommendationPopupComponent } from './attribute-recommendation-dialog.component';
import { AttributeRecommendationDeletePopupComponent } from './attribute-recommendation-delete-dialog.component';

export const attributeRecommendationRoute: Routes = [
    {
        path: 'attribute-recommendation',
        component: AttributeRecommendationComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.attributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'attribute-recommendation/:id',
        component: AttributeRecommendationDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.attributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attributeRecommendationPopupRoute: Routes = [
    {
        path: 'attribute-recommendation-new',
        component: AttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.attributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attribute-recommendation/:id/edit',
        component: AttributeRecommendationPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.attributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'attribute-recommendation/:id/delete',
        component: AttributeRecommendationDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.attributeRecommendation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
