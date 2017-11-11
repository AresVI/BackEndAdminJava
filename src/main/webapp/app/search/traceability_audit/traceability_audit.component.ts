import {Component, OnInit} from '@angular/core';

import {ActivatedRoute, Router} from '@angular/router';

import { Principal } from '../../shared';
import {ITEMS_PER_PAGE} from '../../shared/constants/pagination.constants';
import {Subscription} from 'rxjs/Subscription';
import {TraceabilityAudit} from '../../entities/traceability-audit/traceability-audit.model';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';
import {TraceabilityAuditService} from '../../entities/traceability-audit/traceability-audit.service';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';
import {CompanyService} from '../../entities/company/company.service';
import {Company} from '../../entities/company/company.model';
import {AuditorService} from '../../entities/auditor/auditor.service';
import {Auditor} from '../../entities/auditor/auditor.model';

@Component({
    selector: 'jhi-flow-audit-dashboard',
    templateUrl: './traceability_audit.component.html',
})

export class SearchTraceabilityAuditComponent implements OnInit {
    currentAccount: Account;
    traceabilityAudits: TraceabilityAudit[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    companies: Company[];
    auditors: Auditor[];
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    filterCompany: number;
    filterCategory: string;

    constructor(
        private traceabilityAuditService: TraceabilityAuditService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private companyService: CompanyService,
        private auditorService: AuditorService,
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    ngOnInit() {

        this.filterCategory = null;
        this.filterCompany = 0;

        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTraceabilityAudits();
        this.loadAllCompanies();
        this.loadAllAuditors();
    }

    loadAll() {
        if (this.filterCategory === 'null') { this.filterCategory = null; }
        this.traceabilityAuditService.queryFinished(
            {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()},
            {
                category: this.filterCategory,
                company_id: this.filterCompany
            }).subscribe(
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

    loadAllCompanies() {
        this.companyService.queryAll({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessCompanies(res.json),
            (res: ResponseWrapper) => this.onErrorCompanies(res.json)
        );
    }

    loadAllAuditors() {
        this.auditorService.queryExternals({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessAuditors(res.json),
            (res: ResponseWrapper) => this.onErrorAuditors(res.json)
        );
    }

    transition() {
        this.router.navigate(['/search/traceability_audit'], {queryParams:
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
        this.router.navigate(['/search/traceability_audit', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }

    trackId(index: number, item: TraceabilityAudit) {
        return item.id;
    }
    registerChangeInTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe('traceabilityAuditListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccessCompanies(data) {
        this.companies = data;
    }
    private onErrorCompanies(error) {
        this.alertService.error(error.message, null, null);
    }

    private onSuccessAuditors(data) {
        this.auditors = data;
    }
    private onErrorAuditors(error) {
        this.alertService.error(error.message, null, null);
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.traceabilityAudits = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
