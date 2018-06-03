import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { TraceAudit } from './trace-audit.model';
import { TraceAuditService } from './trace-audit.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-trace-audit',
    templateUrl: './trace-audit.component.html'
})
export class TraceAuditComponent implements OnInit, OnDestroy {
    traceAudits: TraceAudit[];
    currentAccount: any;
    eventSubscriber: Subscription;
    traceabilityAuditId: number;
    private subscription: Subscription;

    constructor(
        private traceAuditService: TraceAuditService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private route: ActivatedRoute
    ) {
    }

    loadAll() {
        this.traceAuditService.query(this.traceabilityAuditId).subscribe(
            (res: ResponseWrapper) => {
                this.traceAudits = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.traceabilityAuditId = params['traceabilityAuditId'];
            this.loadAll();
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTraceAudits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TraceAudit) {
        return item.id;
    }
    registerChangeInTraceAudits() {
        this.eventSubscriber = this.eventManager.subscribe('traceAuditListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
