import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { AuditProcessRecommendation } from './audit-process-recommendation.model';
import { AuditProcessRecommendationPopupService } from './audit-process-recommendation-popup.service';
import { AuditProcessRecommendationService } from './audit-process-recommendation.service';
import { Recommendation, RecommendationService } from '../recommendation';
import { AuditProcess, AuditProcessService } from '../audit-process';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-process-recommendation-dialog',
    templateUrl: './audit-process-recommendation-dialog.component.html'
})
export class AuditProcessRecommendationDialogComponent implements OnInit {

    auditProcessRecommendation: AuditProcessRecommendation;
    isSaving: boolean;

    recommendations: Recommendation[];

    auditprocesses: AuditProcess[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private auditProcessRecommendationService: AuditProcessRecommendationService,
        private recommendationService: RecommendationService,
        private auditProcessService: AuditProcessService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.recommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.recommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.auditProcessService.query()
            .subscribe((res: ResponseWrapper) => { this.auditprocesses = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        if (this.auditProcessRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditProcessRecommendationService.update(this.auditProcessRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.auditProcessRecommendationService.create(this.auditProcessRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditProcessRecommendation>) {
        result.subscribe((res: AuditProcessRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditProcessRecommendation) {
        this.eventManager.broadcast({ name: 'auditProcessRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackRecommendationById(index: number, item: Recommendation) {
        return item.id;
    }

    trackAuditProcessById(index: number, item: AuditProcess) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-audit-process-recommendation-popup',
    template: ''
})
export class AuditProcessRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessRecommendationPopupService: AuditProcessRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditProcessRecommendationPopupService
                    .open(AuditProcessRecommendationDialogComponent as Component, params['id']);
            } else {
                this.auditProcessRecommendationPopupService
                    .open(AuditProcessRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
