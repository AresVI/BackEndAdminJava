import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CancelationTraceabilityAudit } from './cancelation-traceability-audit.model';
import { CancelationTraceabilityAuditPopupService } from './cancelation-traceability-audit-popup.service';
import { CancelationTraceabilityAuditService } from './cancelation-traceability-audit.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-cancelation-traceability-audit-dialog',
    templateUrl: './cancelation-traceability-audit-dialog.component.html'
})
export class CancelationTraceabilityAuditDialogComponent implements OnInit {

    cancelationTraceabilityAudit: CancelationTraceabilityAudit;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private cancelationTraceabilityAuditService: CancelationTraceabilityAuditService,
        private traceabilityAuditService: TraceabilityAuditService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService
            .query({filter: 'cancelationtraceabilityaudit-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.cancelationTraceabilityAudit.traceabilityAuditId) {
                    this.traceabilityaudits = res.json;
                } else {
                    this.traceabilityAuditService
                        .find(this.cancelationTraceabilityAudit.traceabilityAuditId)
                        .subscribe((subRes: TraceabilityAudit) => {
                            this.traceabilityaudits = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.userService.query()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.cancelationTraceabilityAudit.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cancelationTraceabilityAuditService.update(this.cancelationTraceabilityAudit));
        } else {
            this.subscribeToSaveResponse(
                this.cancelationTraceabilityAuditService.create(this.cancelationTraceabilityAudit));
        }
    }

    private subscribeToSaveResponse(result: Observable<CancelationTraceabilityAudit>) {
        result.subscribe((res: CancelationTraceabilityAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CancelationTraceabilityAudit) {
        this.eventManager.broadcast({ name: 'cancelationTraceabilityAuditListModification', content: 'OK'});
        this.eventManager.broadcast({ name: 'traceabilityAuditListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-cancelation-traceability-audit-popup',
    template: ''
})
export class CancelationTraceabilityAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cancelationTraceabilityAuditPopupService: CancelationTraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {

            if ( params['id'] ) {
                this.cancelationTraceabilityAuditPopupService
                    .open(CancelationTraceabilityAuditDialogComponent as Component, params['traceability_audit'], params['id']);
            } else {
                this.cancelationTraceabilityAuditPopupService
                    .open(CancelationTraceabilityAuditDialogComponent as Component, params['traceability_audit']);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
