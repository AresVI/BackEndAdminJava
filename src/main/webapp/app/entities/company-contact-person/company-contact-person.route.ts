import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CompanyContactPersonComponent } from './company-contact-person.component';
import {
    CompanyContactPersonDetailComponent, CompanyContactPersonDetailPopupComponent
} from './company-contact-person-detail.component';
import { CompanyContactPersonPopupComponent } from './company-contact-person-dialog.component';
import { CompanyContactPersonDeletePopupComponent } from './company-contact-person-delete-dialog.component';

@Injectable()
export class CompanyContactPersonResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const company_contact_personRoute: Routes = [
    {
        path: 'company/:company_id/company-contact-person',
        component: CompanyContactPersonComponent,
        resolve: {
            'pagingParams': CompanyContactPersonResolvePagingParams
        },
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'company/:company_id/company-contact-person/:id',
        component: CompanyContactPersonDetailPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE', 'ROLE_AUDITOR_EXTERNAL'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

export const company_contact_personPopupRoute: Routes = [
    {
        path: 'company/:company_id/company-contact-person-new',
        component: CompanyContactPersonPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'company/:company_id/company-contact-person/:id/edit',
        component: CompanyContactPersonPopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR', 'ROLE_ADMINISTRATIVE'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'company/:company_id/company-contact-person/:id/delete',
        component: CompanyContactPersonDeletePopupComponent,
        data: {
            authorities: ['ROLE_ADMINISTRATOR'],
            pageTitle: 'aresViApp.company_contact_person.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
