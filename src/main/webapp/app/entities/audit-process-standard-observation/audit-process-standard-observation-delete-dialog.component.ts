import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditProcessStandardObservation } from './audit-process-standard-observation.model';
import { AuditProcessStandardObservationPopupService } from './audit-process-standard-observation-popup.service';
import { AuditProcessStandardObservationService } from './audit-process-standard-observation.service';

@Component({
    selector: 'jhi-audit-process-standard-observation-delete-dialog',
    templateUrl: './audit-process-standard-observation-delete-dialog.component.html'
})
export class AuditProcessStandardObservationDeleteDialogComponent {

    auditProcessStandardObservation: AuditProcessStandardObservation;

    constructor(
        private auditProcessStandardObservationService: AuditProcessStandardObservationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditProcessStandardObservationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditProcessStandardObservationListModification',
                content: 'Deleted an auditProcessStandardObservation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-process-standard-observation-delete-popup',
    template: ''
})
export class AuditProcessStandardObservationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessStandardObservationPopupService: AuditProcessStandardObservationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditProcessStandardObservationPopupService
                .open(AuditProcessStandardObservationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
