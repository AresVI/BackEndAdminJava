import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AuditTaskStandardObservation } from './audit-task-standard-observation.model';
import { AuditTaskStandardObservationPopupService } from './audit-task-standard-observation-popup.service';
import { AuditTaskStandardObservationService } from './audit-task-standard-observation.service';

@Component({
    selector: 'jhi-audit-task-standard-observation-dialog',
    templateUrl: './audit-task-standard-observation-dialog.component.html'
})
export class AuditTaskStandardObservationDialogComponent implements OnInit {

    auditTaskStandardObservation: AuditTaskStandardObservation;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditTaskStandardObservationService: AuditTaskStandardObservationService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.auditTaskStandardObservation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditTaskStandardObservationService.update(this.auditTaskStandardObservation));
        } else {
            this.subscribeToSaveResponse(
                this.auditTaskStandardObservationService.create(this.auditTaskStandardObservation));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditTaskStandardObservation>) {
        result.subscribe((res: AuditTaskStandardObservation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditTaskStandardObservation) {
        this.eventManager.broadcast({ name: 'auditTaskStandardObservationListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-audit-task-standard-observation-popup',
    template: ''
})
export class AuditTaskStandardObservationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskStandardObservationPopupService: AuditTaskStandardObservationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditTaskStandardObservationPopupService
                    .open(AuditTaskStandardObservationDialogComponent as Component, params['id']);
            } else {
                this.auditTaskStandardObservationPopupService
                    .open(AuditTaskStandardObservationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
