import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttrRecommendation } from './category-attr-recommendation.model';
import { CategoryAttrRecommendationPopupService } from './category-attr-recommendation-popup.service';
import { CategoryAttrRecommendationService } from './category-attr-recommendation.service';
import { AuditTaskRecommendation, AuditTaskRecommendationService } from '../audit-task-recommendation';
import { CategoryAttribute, CategoryAttributeService } from '../category-attribute';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-category-attr-recommendation-dialog',
    templateUrl: './category-attr-recommendation-dialog.component.html'
})
export class CategoryAttrRecommendationDialogComponent implements OnInit {

    categoryAttrRecommendation: CategoryAttrRecommendation;
    isSaving: boolean;

    audittaskrecommendations: AuditTaskRecommendation[];

    categoryattributes: CategoryAttribute[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private categoryAttrRecommendationService: CategoryAttrRecommendationService,
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
        if (this.categoryAttrRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryAttrRecommendationService.update(this.categoryAttrRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.categoryAttrRecommendationService.create(this.categoryAttrRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<CategoryAttrRecommendation>) {
        result.subscribe((res: CategoryAttrRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CategoryAttrRecommendation) {
        this.eventManager.broadcast({ name: 'categoryAttrRecommendationListModification', content: 'OK'});
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
    selector: 'jhi-category-attr-recommendation-popup',
    template: ''
})
export class CategoryAttrRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttrRecommendationPopupService: CategoryAttrRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryAttrRecommendationPopupService
                    .open(CategoryAttrRecommendationDialogComponent as Component, params['id']);
            } else {
                this.categoryAttrRecommendationPopupService
                    .open(CategoryAttrRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
