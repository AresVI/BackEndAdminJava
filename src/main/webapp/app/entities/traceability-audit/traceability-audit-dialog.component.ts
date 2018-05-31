import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditPopupService } from './traceability-audit-popup.service';
import { TraceabilityAuditService } from './traceability-audit.service';
import { Company, CompanyService } from '../company';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-traceability-audit-dialog',
    templateUrl: './traceability-audit-dialog.component.html'
})
export class TraceabilityAuditDialogComponent implements OnInit {

    traceabilityAudit: TraceabilityAudit;
    isSaving: boolean;

    companies: Company[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private traceabilityAuditService: TraceabilityAuditService,
        private companyService: CompanyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: ResponseWrapper) => { this.companies = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.traceabilityAudit.id !== undefined) {
            this.subscribeToSaveResponse(
                this.traceabilityAuditService.update(this.traceabilityAudit));
        } else {
            this.subscribeToSaveResponse(
                this.traceabilityAuditService.create(this.traceabilityAudit));
        }
    }

    private subscribeToSaveResponse(result: Observable<TraceabilityAudit>) {
        result.subscribe((res: TraceabilityAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TraceabilityAudit) {
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

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-traceability-audit-popup',
    template: ''
})
export class TraceabilityAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceabilityAuditPopupService: TraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.traceabilityAuditPopupService
                    .open(TraceabilityAuditDialogComponent as Component, params['id']);
            } else {
                this.traceabilityAuditPopupService
                    .open(TraceabilityAuditDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
