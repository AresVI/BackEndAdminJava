import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CancellationTraceabilityAudit } from './cancellation-traceability-audit.model';
import { CancellationTraceabilityAuditPopupService } from './cancellation-traceability-audit-popup.service';
import { CancellationTraceabilityAuditService } from './cancellation-traceability-audit.service';

@Component({
    selector: 'jhi-cancellation-traceability-audit-delete-dialog',
    templateUrl: './cancellation-traceability-audit-delete-dialog.component.html'
})
export class CancellationTraceabilityAuditDeleteDialogComponent {

    cancellationTraceabilityAudit: CancellationTraceabilityAudit;

    constructor(
        private cancellationTraceabilityAuditService: CancellationTraceabilityAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cancellationTraceabilityAuditService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'cancellationTraceabilityAuditListModification',
                content: 'Deleted an cancellationTraceabilityAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cancellation-traceability-audit-delete-popup',
    template: ''
})
export class CancellationTraceabilityAuditDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private cancellationTraceabilityAuditPopupService: CancellationTraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.cancellationTraceabilityAuditPopupService
                .open(CancellationTraceabilityAuditDeleteDialogComponent as Component, 0, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
