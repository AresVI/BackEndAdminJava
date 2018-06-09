import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RecommendationAttributeRecommendation } from './recommendation-attribute-recommendation.model';
import { RecommendationAttributeRecommendationPopupService } from './recommendation-attribute-recommendation-popup.service';
import { RecommendationAttributeRecommendationService } from './recommendation-attribute-recommendation.service';
import { RecommendationAttribute, RecommendationAttributeService } from '../recommendation-attribute';
import { Recommendation, RecommendationService } from '../recommendation';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-recommendation-attribute-recommendation-dialog',
    templateUrl: './recommendation-attribute-recommendation-dialog.component.html'
})
export class RecommendationAttributeRecommendationDialogComponent implements OnInit {

    recommendationAttributeRecommendation: RecommendationAttributeRecommendation;
    isSaving: boolean;

    recommendationattributes: RecommendationAttribute[];

    recommendations: Recommendation[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private recommendationAttributeRecommendationService: RecommendationAttributeRecommendationService,
        private recommendationAttributeService: RecommendationAttributeService,
        private recommendationService: RecommendationService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.recommendationAttributeService.query()
            .subscribe((res: ResponseWrapper) => { this.recommendationattributes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.recommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.recommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.recommendationAttributeRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.recommendationAttributeRecommendationService.update(this.recommendationAttributeRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.recommendationAttributeRecommendationService.create(this.recommendationAttributeRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<RecommendationAttributeRecommendation>) {
        result.subscribe((res: RecommendationAttributeRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: RecommendationAttributeRecommendation) {
        this.eventManager.broadcast({ name: 'recommendationAttributeRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackRecommendationAttributeById(index: number, item: RecommendationAttribute) {
        return item.id;
    }

    trackRecommendationById(index: number, item: Recommendation) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-recommendation-attribute-recommendation-popup',
    template: ''
})
export class RecommendationAttributeRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationAttributeRecommendationPopupService: RecommendationAttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.recommendationAttributeRecommendationPopupService
                    .open(RecommendationAttributeRecommendationDialogComponent as Component, params['id']);
            } else {
                this.recommendationAttributeRecommendationPopupService
                    .open(RecommendationAttributeRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
