import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { AuditTaskRecommendationService } from './audit-task-recommendation.service';
import {Observable} from 'rxjs/Observable';

@Component({
    selector: 'jhi-audit-task-recommendation-detail',
    templateUrl: './audit-task-recommendation-detail.component.html'
})
export class AuditTaskRecommendationDetailComponent implements OnInit, OnDestroy {

    auditTaskRecommendation: AuditTaskRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    isSaving: boolean;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        private route: ActivatedRoute,
        private router: Router,
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

    save() {
        this.isSaving = true;
        this.subscribeToSaveResponse(
            this.auditTaskRecommendationService.update(this.auditTaskRecommendation));
    }

    private subscribeToSaveResponse(result: Observable<AuditTaskRecommendation>) {
        result.subscribe((res: AuditTaskRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditTaskRecommendation) {
        this.eventManager.broadcast({ name: 'auditTaskRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.router.navigate(['/audit-process-recommendation', this.auditTaskRecommendation.auditProcessRecomId]);
    }

    private onSaveError() {
        this.isSaving = false;
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
