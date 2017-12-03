import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AuditProcessStandardObservation } from './audit-process-standard-observation.model';
import { AuditProcessStandardObservationPopupService } from './audit-process-standard-observation-popup.service';
import { AuditProcessStandardObservationService } from './audit-process-standard-observation.service';

@Component({
    selector: 'jhi-audit-process-standard-observation-dialog',
    templateUrl: './audit-process-standard-observation-dialog.component.html'
})
export class AuditProcessStandardObservationDialogComponent implements OnInit {

    auditProcessStandardObservation: AuditProcessStandardObservation;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditProcessStandardObservationService: AuditProcessStandardObservationService,
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
        if (this.auditProcessStandardObservation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditProcessStandardObservationService.update(this.auditProcessStandardObservation));
        } else {
            this.subscribeToSaveResponse(
                this.auditProcessStandardObservationService.create(this.auditProcessStandardObservation));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditProcessStandardObservation>) {
        result.subscribe((res: AuditProcessStandardObservation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditProcessStandardObservation) {
        this.eventManager.broadcast({ name: 'auditProcessStandardObservationListModification', content: 'OK'});
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
    selector: 'jhi-audit-process-standard-observation-popup',
    template: ''
})
export class AuditProcessStandardObservationPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessStandardObservationPopupService: AuditProcessStandardObservationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditProcessStandardObservationPopupService
                    .open(AuditProcessStandardObservationDialogComponent as Component, params['id']);
            } else {
                this.auditProcessStandardObservationPopupService
                    .open(AuditProcessStandardObservationDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
