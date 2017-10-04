import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { AuditTaskRecommendationPopupService } from './audit-task-recommendation-popup.service';
import { AuditTaskRecommendationService } from './audit-task-recommendation.service';
import { AuditProcessRecommendation, AuditProcessRecommendationService } from '../audit-process-recommendation';
import { AuditTask, AuditTaskService } from '../audit-task';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-task-recommendation-dialog',
    templateUrl: './audit-task-recommendation-dialog.component.html'
})
export class AuditTaskRecommendationDialogComponent implements OnInit {

    auditTaskRecommendation: AuditTaskRecommendation;
    isSaving: boolean;

    auditprocessrecommendations: AuditProcessRecommendation[];

    audittasks: AuditTask[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        private auditProcessRecommendationService: AuditProcessRecommendationService,
        private auditTaskService: AuditTaskService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.auditProcessRecommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.auditprocessrecommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.auditTaskService.query()
            .subscribe((res: ResponseWrapper) => { this.audittasks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        if (this.auditTaskRecommendation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditTaskRecommendationService.update(this.auditTaskRecommendation));
        } else {
            this.subscribeToSaveResponse(
                this.auditTaskRecommendationService.create(this.auditTaskRecommendation));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditTaskRecommendation>) {
        result.subscribe((res: AuditTaskRecommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditTaskRecommendation) {
        this.eventManager.broadcast({ name: 'auditTaskRecommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackAuditProcessRecommendationById(index: number, item: AuditProcessRecommendation) {
        return item.id;
    }

    trackAuditTaskById(index: number, item: AuditTask) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-audit-task-recommendation-popup',
    template: ''
})
export class AuditTaskRecommendationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskRecommendationPopupService: AuditTaskRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditTaskRecommendationPopupService
                    .open(AuditTaskRecommendationDialogComponent as Component, params['id']);
            } else {
                this.auditTaskRecommendationPopupService
                    .open(AuditTaskRecommendationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
