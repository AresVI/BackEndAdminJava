import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { AuditAttributeAnalysisService } from './audit-attribute-analysis.service';

@Component({
    selector: 'jhi-audit-attribute-analysis-detail',
    templateUrl: './audit-attribute-analysis-detail.component.html'
})
export class AuditAttributeAnalysisDetailComponent implements OnInit, OnDestroy {

    auditAttributeAnalysis: AuditAttributeAnalysis;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private auditAttributeAnalysisService: AuditAttributeAnalysisService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditAttributeAnalyses();
    }

    load(id) {
        this.auditAttributeAnalysisService.find(id).subscribe((auditAttributeAnalysis) => {
            this.auditAttributeAnalysis = auditAttributeAnalysis;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditAttributeAnalyses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditAttributeAnalysisListModification',
            (response) => this.load(this.auditAttributeAnalysis.id)
        );
    }
}
