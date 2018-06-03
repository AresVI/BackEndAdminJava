import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TraceAudit } from './trace-audit.model';
import { TraceAuditPopupService } from './trace-audit-popup.service';
import { TraceAuditService } from './trace-audit.service';

@Component({
    selector: 'jhi-trace-audit-delete-dialog',
    templateUrl: './trace-audit-delete-dialog.component.html'
})
export class TraceAuditDeleteDialogComponent {

    traceAudit: TraceAudit;

    constructor(
        private traceAuditService: TraceAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.traceAuditService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traceAuditListModification',
                content: 'Deleted an traceAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trace-audit-delete-popup',
    template: ''
})
export class TraceAuditDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceAuditPopupService: TraceAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traceAuditPopupService
                .open(TraceAuditDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
