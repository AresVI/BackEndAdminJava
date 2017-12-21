import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { AuditAttributeAnalysisPopupService } from './audit-attribute-analysis-popup.service';
import { AuditAttributeAnalysisService } from './audit-attribute-analysis.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-attribute-analysis-dialog',
    templateUrl: './audit-attribute-analysis-dialog.component.html'
})
export class AuditAttributeAnalysisDialogComponent implements OnInit {

    auditAttributeAnalysis: AuditAttributeAnalysis;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditAttributeAnalysisService: AuditAttributeAnalysisService,
        private traceabilityAuditService: TraceabilityAuditService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService
            .query({filter: 'auditattributeanalysis-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.auditAttributeAnalysis.traceabilityAuditId) {
                    this.traceabilityaudits = res.json;
                } else {
                    this.traceabilityAuditService
                        .find(this.auditAttributeAnalysis.traceabilityAuditId)
                        .subscribe((subRes: TraceabilityAudit) => {
                            this.traceabilityaudits = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.auditAttributeAnalysis.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditAttributeAnalysisService.update(this.auditAttributeAnalysis));
        } else {
            this.subscribeToSaveResponse(
                this.auditAttributeAnalysisService.create(this.auditAttributeAnalysis));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditAttributeAnalysis>) {
        result.subscribe((res: AuditAttributeAnalysis) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditAttributeAnalysis) {
        this.eventManager.broadcast({ name: 'auditAttributeAnalysisListModification', content: 'OK'});
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
    selector: 'jhi-audit-attribute-analysis-popup',
    template: ''
})
export class AuditAttributeAnalysisPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditAttributeAnalysisPopupService: AuditAttributeAnalysisPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditAttributeAnalysisPopupService
                    .open(AuditAttributeAnalysisDialogComponent as Component, params['id']);
            } else {
                this.auditAttributeAnalysisPopupService
                    .open(AuditAttributeAnalysisDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
