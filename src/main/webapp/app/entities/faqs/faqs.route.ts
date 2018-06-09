import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { FaqsComponent } from './faqs.component';
import { FaqsDetailComponent } from './faqs-detail.component';
import { FaqsPopupComponent } from './faqs-dialog.component';
import { FaqsDeletePopupComponent } from './faqs-delete-dialog.component';

export const faqsRoute: Routes = [
    {
        path: 'faqs',
        component: FaqsComponent,
        data: {
            pageTitle: 'aresViApp.faqs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'faqs/:id',
        component: FaqsDetailComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATIVE', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.faqs.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const faqsPopupRoute: Routes = [
    {
        path: 'faqs-new',
        component: FaqsPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATIVE', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.faqs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'faqs/:id/edit',
        component: FaqsPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATIVE', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.faqs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'faqs/:id/delete',
        component: FaqsDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATIVE', 'ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.faqs.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
