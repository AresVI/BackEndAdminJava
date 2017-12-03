import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditTaskStandardObservation } from './audit-task-standard-observation.model';
import { AuditTaskStandardObservationPopupService } from './audit-task-standard-observation-popup.service';
import { AuditTaskStandardObservationService } from './audit-task-standard-observation.service';

@Component({
    selector: 'jhi-audit-task-standard-observation-delete-dialog',
    templateUrl: './audit-task-standard-observation-delete-dialog.component.html'
})
export class AuditTaskStandardObservationDeleteDialogComponent {

    auditTaskStandardObservation: AuditTaskStandardObservation;

    constructor(
        private auditTaskStandardObservationService: AuditTaskStandardObservationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditTaskStandardObservationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditTaskStandardObservationListModification',
                content: 'Deleted an auditTaskStandardObservation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-task-standard-observation-delete-popup',
    template: ''
})
export class AuditTaskStandardObservationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskStandardObservationPopupService: AuditTaskStandardObservationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditTaskStandardObservationPopupService
                .open(AuditTaskStandardObservationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
