import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { AttributeRecommendation } from './attribute-recommendation.model';
import { AttributeRecommendationPopupService } from './attribute-recommendation-popup.service';
import { AttributeRecommendationService } from './attribute-recommendation.service';
import { CategoryAttRecommendation, CategoryAttRecommendationService } from '../category-att-recommendation';
import { Attribute, AttributeService } from '../attribute';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attribute-recommendation-dialog',
    templateUrl: './attribute-recommendation-dialog.component.html'
})
export class AttributeRecommendationDialogComponent implements OnInit {

    attributeRecommendation: AttributeRecommendation;
    isSaving: boolean;

    categoryattrecommendations: CategoryAttRecommendation[];

    attributes: Attribute[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private attributeRecommendationService: AttributeRecommendationService,
        private categoryAttRecommendationService: CategoryAttRecommendationService,
        private attributeService: AttributeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.categoryAttRecommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.categoryattrecommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.attributeService.query()
            .subscribe((res: ResponseWrapper) => { this.attributes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.attributeRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.attributeRecommendationService.update(this.attributeRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.attributeRecommendationService.create(this.attributeRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<AttributeRecommendation>) {
        result.subscribe((res: AttributeRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AttributeRecommendation) {
        this.eventManager.broadcast({ name: 'attributeRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackCategoryAttRecommendationById(index: number, item: CategoryAttRecommendation) {
        return item.id;
    }

    trackAttributeById(index: number, item: Attribute) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-attribute-recommendation-popup',
    template: ''
})
export class AttributeRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attributeRecommendationPopupService: AttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.attributeRecommendationPopupService
                    .open(AttributeRecommendationDialogComponent as Component, params['id']);
            } else {
                this.attributeRecommendationPopupService
                    .open(AttributeRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
