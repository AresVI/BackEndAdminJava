import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditPopupService } from './traceability-audit-popup.service';
import { TraceabilityAuditService } from './traceability-audit.service';

@Component({
    selector: 'jhi-traceability-audit-delete-dialog',
    templateUrl: './traceability-audit-delete-dialog.component.html'
})
export class TraceabilityAuditDeleteDialogComponent {

    traceabilityAudit: TraceabilityAudit;

    constructor(
        private traceabilityAuditService: TraceabilityAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.traceabilityAuditService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traceabilityAuditListModification',
                content: 'Deleted an traceabilityAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-traceability-audit-delete-popup',
    template: ''
})
export class TraceabilityAuditDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceabilityAuditPopupService: TraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traceabilityAuditPopupService
                .open(TraceabilityAuditDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
