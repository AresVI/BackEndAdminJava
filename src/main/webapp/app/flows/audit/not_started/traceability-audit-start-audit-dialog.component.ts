import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {TraceabilityAudit} from '../../../entities/traceability-audit/traceability-audit.model';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';
import {TraceabilityAuditPopupService} from '../../../entities/traceability-audit/traceability-audit-popup.service';

@Component({
    selector: 'jhi-traceability-audit-delete-dialog',
    templateUrl: './traceability-audit-start-audit-dialog.component.html'
})
export class TraceabilityAuditStartAuditDialogComponent {

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

    confirmStart(id: number) {
        this.traceabilityAuditService.start(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traceabilityAuditListModification',
                content: 'Started an traceabilityAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-traceability-audit-delete-popup',
    template: ''
})
export class TraceabilityAuditStartAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceabilityAuditPopupService: TraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traceabilityAuditPopupService
                .open(TraceabilityAuditStartAuditDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
