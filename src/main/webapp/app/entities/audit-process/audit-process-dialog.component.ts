import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AuditProcess } from './audit-process.model';
import { AuditProcessPopupService } from './audit-process-popup.service';
import { AuditProcessService } from './audit-process.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-process-dialog',
    templateUrl: './audit-process-dialog.component.html'
})
export class AuditProcessDialogComponent implements OnInit {

    auditProcess: AuditProcess;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditProcessService: AuditProcessService,
        private traceabilityAuditService: TraceabilityAuditService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService.query()
            .subscribe((res: ResponseWrapper) => { this.traceabilityaudits = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.auditProcess.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditProcessService.update(this.auditProcess));
        } else {
            this.subscribeToSaveResponse(
                this.auditProcessService.create(this.auditProcess));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditProcess>) {
        result.subscribe((res: AuditProcess) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditProcess) {
        this.eventManager.broadcast({ name: 'auditProcessListModification', content: 'OK'});
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
    selector: 'jhi-audit-process-popup',
    template: ''
})
export class AuditProcessPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessPopupService: AuditProcessPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditProcessPopupService
                    .open(AuditProcessDialogComponent as Component, params['id']);
            } else {
                this.auditProcessPopupService
                    .open(AuditProcessDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
