import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditService } from './traceability-audit.service';
import {Recommendation} from '../recommendation/recommendation.model';
import {ProfileService} from '../../layouts/profiles/profile.service';
import {Principal} from '../../shared/auth/principal.service';

@Component({
    selector: 'jhi-traceability-audit-detail',
    templateUrl: './traceability-audit-detail.component.html'
})
export class TraceabilityAuditDetailComponent implements OnInit, OnDestroy {

    traceabilityAudit: TraceabilityAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    actionText: string;
    allRecommendationReviewed: boolean;
    description: string;
    standardObservation: string;
    account: Account;

    constructor(
        private eventManager: JhiEventManager,
        private traceabilityAuditService: TraceabilityAuditService,
        private route: ActivatedRoute,
        private principal: Principal,
        private modalService: NgbModal,
    ) {
    }

    ngOnInit() {
        this.allRecommendationReviewed = false;
        this.loadAccount();
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraceabilityAudits();
        this.actionText = 'Realizar Auditoría';
        this.description = '';
        this.standardObservation = '';
    }

    load(id) {
        this.traceabilityAuditService.find(id).subscribe((traceabilityAudit) => {
            this.traceabilityAudit = traceabilityAudit;
            this.checkRecommendationsReview();
            this.getActionText();
        });
    }

    loadAccount() {
        if (!this.account) {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        }
    }

    checkRecommendationsReview() {

        let countRecommendationsReviewed = 0;

        for (let rIndex = 0; rIndex < this.traceabilityAudit.recommendationSet.length; rIndex++) {

            const apr: Recommendation = this.traceabilityAudit.recommendationSet[rIndex];

            if (apr.reviewed) {
                countRecommendationsReviewed += 1;
            }

        }

        if ( this.traceabilityAudit.recommendationSet.length === countRecommendationsReviewed ) {
            this.allRecommendationReviewed = true;
        }

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

    openModalDescription(content, description: string, standardObservation: string) {

        this.description = description;
        this.standardObservation = standardObservation;

        this.modalService.open(content).result.then(() => {
            this.description = '';
            this.standardObservation = '';
            }, (reason) => {
            this.description = '';
            this.standardObservation = '';
        });
    }
}
