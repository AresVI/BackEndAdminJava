import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CompanyContactPerson } from './company-contact-person.model';
import { CompanyContactPersonService } from './company-contact-person.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import {CompanyService} from '../company/company.service';
import {Company} from '../company/company.model';

@Component({
    selector: 'jhi-company-contact-person',
    templateUrl: './company-contact-person.component.html'
})
export class CompanyContactPersonComponent implements OnInit, OnDestroy {

    currentAccount: any;
    company_contact_people: CompanyContactPerson[];
    company: Company;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    company_id: number;

    private subscription: Subscription;

    constructor(
        private company_contact_personService: CompanyContactPersonService,
        private companyService: CompanyService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    loadAll() {
        this.company_contact_personService.query(this.company_id, {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()}).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        this.router.navigate(['/company-contact-person'], {queryParams:
            {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/company-contact-person', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    ngOnInit() {

        this.company = new Company();

        this.subscription = this.route.params.subscribe((params) => {
            this.company_id = params['company_id'];
            this.companyService.find(this.company_id).subscribe((company) => {
                this.company = company;
            });
            this.loadAll();
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCompany_contact_people();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CompanyContactPerson) {
        return item.id;
    }
    registerChangeInCompany_contact_people() {
        this.eventSubscriber = this.eventManager.subscribe('company_contact_personListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.company_contact_people = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
