import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Recommendation } from './recommendation.model';
import { RecommendationPopupService } from './recommendation-popup.service';
import { RecommendationService } from './recommendation.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-recommendation-dialog',
    templateUrl: './recommendation-dialog.component.html'
})
export class RecommendationDialogComponent implements OnInit {

    recommendation: Recommendation;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private recommendationService: RecommendationService,
        private traceabilityAuditService: TraceabilityAuditService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService.query()
            .subscribe((res: ResponseWrapper) => { this.traceabilityaudits = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.recommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.recommendationService.update(this.recommendation));
        } else {
            this.subscribeToSaveResponse(
                this.recommendationService.create(this.recommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<Recommendation>) {
        result.subscribe((res: Recommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Recommendation) {
        this.eventManager.broadcast({ name: 'recommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackTraceabilityAuditById(index: number, item: TraceabilityAudit) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-recommendation-popup',
    template: ''
})
export class RecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationPopupService: RecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.recommendationPopupService
                    .open(RecommendationDialogComponent as Component, params['id']);
            } else {
                this.recommendationPopupService
                    .open(RecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
