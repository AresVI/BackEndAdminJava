import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CancellationTraceabilityAudit } from './cancellation-traceability-audit.model';
import { CancellationTraceabilityAuditPopupService } from './cancellation-traceability-audit-popup.service';
import { CancellationTraceabilityAuditService } from './cancellation-traceability-audit.service';
import { TraceabilityAudit, TraceabilityAuditService } from '../traceability-audit';
import { User, UserService } from '../../shared';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-cancellation-traceability-audit-dialog',
    templateUrl: './cancellation-traceability-audit-dialog.component.html'
})
export class CancellationTraceabilityAuditDialogComponent implements OnInit {

    cancellationTraceabilityAudit: CancellationTraceabilityAudit;
    isSaving: boolean;

    traceabilityaudits: TraceabilityAudit[];

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private cancellationTraceabilityAuditService: CancellationTraceabilityAuditService,
        private traceabilityAuditService: TraceabilityAuditService,
        private userService: UserService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.traceabilityAuditService
            .query({filter: 'cancellationtraceabilityaudit-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.cancellationTraceabilityAudit.traceabilityAuditId) {
                    this.traceabilityaudits = res.json;
                } else {
                    this.traceabilityAuditService
                        .find(this.cancellationTraceabilityAudit.traceabilityAuditId)
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
        if (this.cancellationTraceabilityAudit.id !== undefined) {
            this.subscribeToSaveResponse(
                this.cancellationTraceabilityAuditService.update(this.cancellationTraceabilityAudit));
        } else {
            this.subscribeToSaveResponse(
                this.cancellationTraceabilityAuditService.create(this.cancellationTraceabilityAudit));
        }
    }

    private subscribeToSaveResponse(result: Observable<CancellationTraceabilityAudit>) {
        result.subscribe((res: CancellationTraceabilityAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CancellationTraceabilityAudit) {
        this.eventManager.broadcast({ name: 'cancellationTraceabilityAuditListModification', content: 'OK'});
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
    selector: 'jhi-cancellation-traceability-audit-popup',
    template: ''
})
export class CancellationTraceabilityAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cancellationTraceabilityAuditPopupService: CancellationTraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {

            if ( params['id'] ) {
                this.cancellationTraceabilityAuditPopupService
                    .open(CancellationTraceabilityAuditDialogComponent as Component, params['traceability_audit'], params['id']);
            } else {
                this.cancellationTraceabilityAuditPopupService
                    .open(CancellationTraceabilityAuditDialogComponent as Component, params['traceability_audit']);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
