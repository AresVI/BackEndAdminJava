import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import {JhiEventManager, JhiDataUtils, JhiAlertService} from 'ng-jhipster';

import { AuditProcessRecommendation } from './audit-process-recommendation.model';
import { AuditProcessRecommendationService } from './audit-process-recommendation.service';
import {Observable} from 'rxjs/Observable';
import {AuditTaskRecommendation} from '../audit-task-recommendation/audit-task-recommendation.model';
import {AuditProcessStandardObservation} from '../audit-process-standard-observation/audit-process-standard-observation.model';
import {AuditProcessStandardObservationService} from '../audit-process-standard-observation/audit-process-standard-observation.service';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';

@Component({
    selector: 'jhi-audit-process-recommendation-detail',
    templateUrl: './audit-process-recommendation-detail.component.html'
})
export class AuditProcessRecommendationDetailComponent implements OnInit, OnDestroy {

    auditProcessRecommendation: AuditProcessRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    isSaving: boolean;
    allAuditTaskReviewed: boolean;

    auditProcessStandardObservations: AuditProcessStandardObservation[];

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private auditProcessRecommendationService: AuditProcessRecommendationService,
        private route: ActivatedRoute,
        private alertService: JhiAlertService,
        private router: Router,
        private auditProcessStandardObservationService: AuditProcessStandardObservationService,
    ) {
    }

    ngOnInit() {
        this.allAuditTaskReviewed = false;
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditProcessRecommendations();
        this.loadAllAuditProcessStandardObservations();
    }

    load(id) {
        this.auditProcessRecommendationService.find(id).subscribe((auditProcessRecommendation) => {
            this.auditProcessRecommendation = auditProcessRecommendation;
            this.checkAuditTaskReview();
        });
    }

    checkAuditTaskReview() {
        let countAuditProcessReviewed = 0;

        for (let atrIndex = 0; atrIndex < this.auditProcessRecommendation.auditTaskRecommendationSet.length; atrIndex++) {

            const atr: AuditTaskRecommendation = this.auditProcessRecommendation.auditTaskRecommendationSet[atrIndex];

            if (atr.reviewed) {
                countAuditProcessReviewed += 1;
            }

        }

        if ( this.auditProcessRecommendation.auditTaskRecommendationSet.length === countAuditProcessReviewed ) {
            this.allAuditTaskReviewed = true;
        }
    }

    save() {
        this.isSaving = true;
        this.subscribeToSaveResponse(
            this.auditProcessRecommendationService.update(this.auditProcessRecommendation));
    }

    private subscribeToSaveResponse(result: Observable<AuditProcessRecommendation>) {
        result.subscribe((res: AuditProcessRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditProcessRecommendation) {
        this.eventManager.broadcast({ name: 'auditProcessRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.router.navigate(['/recommendation', this.auditProcessRecommendation.recommendationId]);
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

    registerChangeInAuditProcessRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditProcessRecommendationListModification',
            (response) => this.load(this.auditProcessRecommendation.id)
        );
    }

    private loadAllAuditProcessStandardObservations() {

        this.auditProcessStandardObservationService.queryAll().subscribe(
            (res: ResponseWrapper) => this.onSuccessAuditProcessStandardObservations(res.json, res.headers),
            (res: ResponseWrapper) => this.onErrorAuditProcessStandardObservations(res.json)
        );

    }

    private onSuccessAuditProcessStandardObservations(data, headers) {
        this.auditProcessStandardObservations = data;
    }
    private onErrorAuditProcessStandardObservations(error) {
        this.alertService.error(error.message, null, null);
    }
}
