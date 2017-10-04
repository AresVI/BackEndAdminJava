import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttributeRecommendation } from './category-attribute-recommendation.model';
import { CategoryAttributeRecommendationPopupService } from './category-attribute-recommendation-popup.service';
import { CategoryAttributeRecommendationService } from './category-attribute-recommendation.service';
import { AuditTaskRecommendation, AuditTaskRecommendationService } from '../audit-task-recommendation';
import { CategoryAttribute, CategoryAttributeService } from '../category-attribute';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-category-attribute-recommendation-dialog',
    templateUrl: './category-attribute-recommendation-dialog.component.html'
})
export class CategoryAttributeRecommendationDialogComponent implements OnInit {

    categoryAttributeRecommendation: CategoryAttributeRecommendation;
    isSaving: boolean;

    audittaskrecommendations: AuditTaskRecommendation[];

    categoryattributes: CategoryAttribute[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private categoryAttributeRecommendationService: CategoryAttributeRecommendationService,
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
        if (this.categoryAttributeRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryAttributeRecommendationService.update(this.categoryAttributeRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.categoryAttributeRecommendationService.create(this.categoryAttributeRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<CategoryAttributeRecommendation>) {
        result.subscribe((res: CategoryAttributeRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CategoryAttributeRecommendation) {
        this.eventManager.broadcast({ name: 'categoryAttributeRecommendationListModification', content: 'OK'});
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
    selector: 'jhi-category-attribute-recommendation-popup',
    template: ''
})
export class CategoryAttributeRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttributeRecommendationPopupService: CategoryAttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryAttributeRecommendationPopupService
                    .open(CategoryAttributeRecommendationDialogComponent as Component, params['id']);
            } else {
                this.categoryAttributeRecommendationPopupService
                    .open(CategoryAttributeRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
