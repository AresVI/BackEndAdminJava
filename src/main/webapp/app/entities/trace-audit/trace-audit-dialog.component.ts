import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TraceAudit } from './trace-audit.model';
import { TraceAuditPopupService } from './trace-audit-popup.service';
import { TraceAuditService } from './trace-audit.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { AuditTaskRecommendation, AuditTaskRecommendationService } from '../audit-task-recommendation';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-trace-audit-dialog',
    templateUrl: './trace-audit-dialog.component.html'
})
export class TraceAuditDialogComponent implements OnInit {

    traceAudit: TraceAudit;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    audittaskrecommendations: AuditTaskRecommendation[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private traceAuditService: TraceAuditService,
        private traceabilityAuditService: TraceabilityAuditService,
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService.query()
            .subscribe((res: ResponseWrapper) => { this.traceabilityaudits = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.auditTaskRecommendationService.query()
            .subscribe((res: ResponseWrapper) => { this.audittaskrecommendations = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.traceAudit.id !== undefined) {
            this.subscribeToSaveResponse(
                this.traceAuditService.update(this.traceAudit));
        } else {
            this.subscribeToSaveResponse(
                this.traceAuditService.create(this.traceAudit));
        }
    }

    private subscribeToSaveResponse(result: Observable<TraceAudit>) {
        result.subscribe((res: TraceAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TraceAudit) {
        this.eventManager.broadcast({ name: 'traceAuditListModification', content: 'OK'});
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

    trackAuditTaskRecommendationById(index: number, item: AuditTaskRecommendation) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-trace-audit-popup',
    template: ''
})
export class TraceAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceAuditPopupService: TraceAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.traceAuditPopupService
                    .open(TraceAuditDialogComponent as Component, params['id']);
            } else {
                this.traceAuditPopupService
                    .open(TraceAuditDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
