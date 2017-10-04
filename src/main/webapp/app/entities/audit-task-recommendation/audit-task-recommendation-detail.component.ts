import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { AuditTaskRecommendationService } from './audit-task-recommendation.service';

@Component({
    selector: 'jhi-audit-task-recommendation-detail',
    templateUrl: './audit-task-recommendation-detail.component.html'
})
export class AuditTaskRecommendationDetailComponent implements OnInit, OnDestroy {

    auditTaskRecommendation: AuditTaskRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditTaskRecommendations();
    }

    load(id) {
        this.auditTaskRecommendationService.find(id).subscribe((auditTaskRecommendation) => {
            this.auditTaskRecommendation = auditTaskRecommendation;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditTaskRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditTaskRecommendationListModification',
            (response) => this.load(this.auditTaskRecommendation.id)
        );
    }
}
