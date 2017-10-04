import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttRecommendation } from './category-att-recommendation.model';
import { CategoryAttRecommendationPopupService } from './category-att-recommendation-popup.service';
import { CategoryAttRecommendationService } from './category-att-recommendation.service';
import { AuditTaskRecommendation, AuditTaskRecommendationService } from '../audit-task-recommendation';
import { CategoryAttribute, CategoryAttributeService } from '../category-attribute';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-category-att-recommendation-dialog',
    templateUrl: './category-att-recommendation-dialog.component.html'
})
export class CategoryAttRecommendationDialogComponent implements OnInit {

    categoryAttRecommendation: CategoryAttRecommendation;
    isSaving: boolean;

    audittaskrecommendations: AuditTaskRecommendation[];

    categoryattributes: CategoryAttribute[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private categoryAttRecommendationService: CategoryAttRecommendationService,
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        private categoryAttributeService: CategoryAttributeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.auditTaskRecommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.audittaskrecommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.categoryAttributeService.query()
            .subscribe((res: ResponseWrapper) => { this.categoryattributes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        if (this.categoryAttRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryAttRecommendationService.update(this.categoryAttRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.categoryAttRecommendationService.create(this.categoryAttRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<CategoryAttRecommendation>) {
        result.subscribe((res: CategoryAttRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CategoryAttRecommendation) {
        this.eventManager.broadcast({ name: 'categoryAttRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackAuditTaskRecommendationById(index: number, item: AuditTaskRecommendation) {
        return item.id;
    }

    trackCategoryAttributeById(index: number, item: CategoryAttribute) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-category-att-recommendation-popup',
    template: ''
})
export class CategoryAttRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttRecommendationPopupService: CategoryAttRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryAttRecommendationPopupService
                    .open(CategoryAttRecommendationDialogComponent as Component, params['id']);
            } else {
                this.categoryAttRecommendationPopupService
                    .open(CategoryAttRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
