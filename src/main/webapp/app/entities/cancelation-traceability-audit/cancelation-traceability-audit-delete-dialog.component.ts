import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CancelationTraceabilityAudit } from './cancelation-traceability-audit.model';
import { CancelationTraceabilityAuditPopupService } from './cancelation-traceability-audit-popup.service';
import { CancelationTraceabilityAuditService } from './cancelation-traceability-audit.service';

@Component({
    selector: 'jhi-cancelation-traceability-audit-delete-dialog',
    templateUrl: './cancelation-traceability-audit-delete-dialog.component.html'
})
export class CancelationTraceabilityAuditDeleteDialogComponent {

    cancelationTraceabilityAudit: CancelationTraceabilityAudit;

    constructor(
        private cancelationTraceabilityAuditService: CancelationTraceabilityAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cancelationTraceabilityAuditService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cancelationTraceabilityAuditListModification',
                content: 'Deleted an cancelationTraceabilityAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cancelation-traceability-audit-delete-popup',
    template: ''
})
export class CancelationTraceabilityAuditDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cancelationTraceabilityAuditPopupService: CancelationTraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cancelationTraceabilityAuditPopupService
                .open(CancelationTraceabilityAuditDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
