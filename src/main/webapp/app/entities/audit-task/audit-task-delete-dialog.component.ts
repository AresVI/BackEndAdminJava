import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditTask } from './audit-task.model';
import { AuditTaskPopupService } from './audit-task-popup.service';
import { AuditTaskService } from './audit-task.service';

@Component({
    selector: 'jhi-audit-task-delete-dialog',
    templateUrl: './audit-task-delete-dialog.component.html'
})
export class AuditTaskDeleteDialogComponent {

    auditTask: AuditTask;

    constructor(
        private auditTaskService: AuditTaskService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditTaskService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditTaskListModification',
                content: 'Deleted an auditTask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-task-delete-popup',
    template: ''
})
export class AuditTaskDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskPopupService: AuditTaskPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditTaskPopupService
                .open(AuditTaskDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
