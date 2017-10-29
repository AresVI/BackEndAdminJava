import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditService } from './traceability-audit.service';
import {Recommendation} from '../recommendation/recommendation.model';
import {AuditProcessRecommendation} from '../audit-process-recommendation/audit-process-recommendation.model';

@Component({
    selector: 'jhi-traceability-audit-detail',
    templateUrl: './traceability-audit-detail.component.html'
})
export class TraceabilityAuditDetailComponent implements OnInit, OnDestroy {

    traceabilityAudit: TraceabilityAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    actionText: string;

    constructor(
        private eventManager: JhiEventManager,
        private traceabilityAuditService: TraceabilityAuditService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraceabilityAudits();
        this.actionText = 'Realizar Auditoría';
    }

    load(id) {
        this.traceabilityAuditService.find(id).subscribe((traceabilityAudit) => {
            this.traceabilityAudit = traceabilityAudit;
            this.getActionText();
        });
    }

    getActionText() {

        let countProcessReviewed = 0;

        for (let rIndex = 0; rIndex < this.traceabilityAudit.recommendationSet.length; rIndex++) {

            const r: Recommendation = this.traceabilityAudit.recommendationSet[rIndex];

            for (let aprIndex = 0; aprIndex < r.auditProcessRecommendationSet.length; aprIndex++) {

                if (r.auditProcessRecommendationSet[aprIndex].reviewed) {
                    countProcessReviewed += 1;
                }

            }

        }

        if (countProcessReviewed > 0) {

            if (this.traceabilityAudit.recommendationSet.length === countProcessReviewed) {
                this.actionText = 'Revisar Auditoría';
            } else {
                this.actionText = 'Continuar Auditoría';
            }

        }

    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traceabilityAuditListModification',
            (response) => this.load(this.traceabilityAudit.id)
        );
    }
}
