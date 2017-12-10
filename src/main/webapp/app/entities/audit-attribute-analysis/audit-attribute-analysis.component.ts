import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { AuditAttributeAnalysisService } from './audit-attribute-analysis.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-audit-attribute-analysis',
    templateUrl: './audit-attribute-analysis.component.html'
})
export class AuditAttributeAnalysisComponent implements OnInit, OnDestroy {
auditAttributeAnalyses: AuditAttributeAnalysis[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private auditAttributeAnalysisService: AuditAttributeAnalysisService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.auditAttributeAnalysisService.query().subscribe(
            (res: ResponseWrapper) => {
                this.auditAttributeAnalyses = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAuditAttributeAnalyses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AuditAttributeAnalysis) {
        return item.id;
    }
    registerChangeInAuditAttributeAnalyses() {
        this.eventSubscriber = this.eventManager.subscribe('auditAttributeAnalysisListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
