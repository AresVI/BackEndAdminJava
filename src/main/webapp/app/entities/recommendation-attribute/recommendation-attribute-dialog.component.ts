import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RecommendationAttribute } from './recommendation-attribute.model';
import { RecommendationAttributePopupService } from './recommendation-attribute-popup.service';
import { RecommendationAttributeService } from './recommendation-attribute.service';
import { Weighting, WeightingService } from '../weighting';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-recommendation-attribute-dialog',
    templateUrl: './recommendation-attribute-dialog.component.html'
})
export class RecommendationAttributeDialogComponent implements OnInit {

    recommendationAttribute: RecommendationAttribute;
    isSaving: boolean;

    weightings: Weighting[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private recommendationAttributeService: RecommendationAttributeService,
        private weightingService: WeightingService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.weightingService.query()
            .subscribe((res: ResponseWrapper) => { this.weightings = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.recommendationAttribute.id !== undefined) {
            this.subscribeToSaveResponse(
                this.recommendationAttributeService.update(this.recommendationAttribute));
        } else {
            this.subscribeToSaveResponse(
                this.recommendationAttributeService.create(this.recommendationAttribute));
        }
    }

    private subscribeToSaveResponse(result: Observable<RecommendationAttribute>) {
        result.subscribe((res: RecommendationAttribute) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: RecommendationAttribute) {
        this.eventManager.broadcast({ name: 'recommendationAttributeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackWeightingById(index: number, item: Weighting) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-recommendation-attribute-popup',
    template: ''
})
export class RecommendationAttributePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationAttributePopupService: RecommendationAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.recommendationAttributePopupService
                    .open(RecommendationAttributeDialogComponent as Component, params['id']);
            } else {
                this.recommendationAttributePopupService
                    .open(RecommendationAttributeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
