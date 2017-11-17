import {Component, OnInit} from '@angular/core';

import {ActivatedRoute, Router} from '@angular/router';

import { Principal } from '../../../shared';
import {ITEMS_PER_PAGE} from '../../../shared/constants/pagination.constants';
import {Subscription} from 'rxjs/Subscription';
import {TraceabilityAudit} from '../../../entities/traceability-audit/traceability-audit.model';
import {JhiAlertService, JhiEventManager, JhiPaginationUtil, JhiParseLinks} from 'ng-jhipster';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';
import {ResponseWrapper} from '../../../shared/model/response-wrapper.model';
import {CancellationTraceabilityAudit} from '../../../entities/cancellation-traceability-audit/cancellation-traceability-audit.model';
import {CancellationTraceabilityAuditService} from '../../../entities/cancellation-traceability-audit/cancellation-traceability-audit.service';
import {PaginationConfig} from '../../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-flow-audit-dashboard',
    templateUrl: './canceled.component.html',
})

export class CanceledComponent implements OnInit {
    currentAccount: any;
    cancellationTraceabilityAudits: CancellationTraceabilityAudit[];
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

    constructor(
        private cancellationTraceabilityAuditService: CancellationTraceabilityAuditService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private paginationUtil: JhiPaginationUtil,
        private paginationConfig: PaginationConfig
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
        this.cancellationTraceabilityAuditService.query({
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
        this.router.navigate(['/process/audit/status/canceled'], {queryParams:
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
        this.router.navigate(['/process/audit/status/canceled', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCancellationTraceabilityAudits();
    }

    trackId(index: number, item: CancellationTraceabilityAudit) {
        return item.id;
    }

    registerChangeInCancellationTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe('cancellationTraceabilityAuditListModification', (response) => this.loadAll());
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
        this.cancellationTraceabilityAudits = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
