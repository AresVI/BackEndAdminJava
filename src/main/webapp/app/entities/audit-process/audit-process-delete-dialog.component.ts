import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditProcess } from './audit-process.model';
import { AuditProcessPopupService } from './audit-process-popup.service';
import { AuditProcessService } from './audit-process.service';

@Component({
    selector: 'jhi-audit-process-delete-dialog',
    templateUrl: './audit-process-delete-dialog.component.html'
})
export class AuditProcessDeleteDialogComponent {

    auditProcess: AuditProcess;

    constructor(
        private auditProcessService: AuditProcessService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditProcessService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditProcessListModification',
                content: 'Deleted an auditProcess'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-process-delete-popup',
    template: ''
})
export class AuditProcessDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessPopupService: AuditProcessPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditProcessPopupService
                .open(AuditProcessDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
