import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {TraceabilityAuditService} from './traceability-audit.service';
import {TraceabilityAudit} from './traceability-audit.model';
import {TraceabilityAuditPopupService} from './traceability-audit-popup.service';

@Component({
    selector: 'jhi-traceability-audit-finish-dialog',
    templateUrl: './traceability-audit-finish-audit-dialog.component.html'
})
export class TraceabilityAuditFinishAuditDialogComponent {

    traceabilityAudit: TraceabilityAudit;
    operationInProgress = false;

    constructor(
        private traceabilityAuditService: TraceabilityAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmFinish(id: number) {
        this.operationInProgress = true;
        this.traceabilityAuditService.finish(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traceabilityAuditListModification',
                content: 'Finished an traceabilityAudit'
            });
            this.activeModal.dismiss(true);
            this.operationInProgress = false;
        });
    }
}

@Component({
    selector: 'jhi-traceability-audit-delete-popup',
    template: ''
})
export class TraceabilityAuditFinishAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceabilityAuditPopupService: TraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traceabilityAuditPopupService
                .open(TraceabilityAuditFinishAuditDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
