import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AuditTask } from './audit-task.model';
import { AuditTaskPopupService } from './audit-task-popup.service';
import { AuditTaskService } from './audit-task.service';
import { Container, ContainerService } from '../container';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-task-dialog',
    templateUrl: './audit-task-dialog.component.html'
})
export class AuditTaskDialogComponent implements OnInit {

    auditTask: AuditTask;
    isSaving: boolean;

    containers: Container[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditTaskService: AuditTaskService,
        private containerService: ContainerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.containerService.query()
            .subscribe((res: ResponseWrapper) => { this.containers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.auditTask.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditTaskService.update(this.auditTask));
        } else {
            this.subscribeToSaveResponse(
                this.auditTaskService.create(this.auditTask));
        }
    }

    private subscribeToSaveResponse(result: Observable<AuditTask>) {
        result.subscribe((res: AuditTask) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AuditTask) {
        this.eventManager.broadcast({ name: 'auditTaskListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackContainerById(index: number, item: Container) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-audit-task-popup',
    template: ''
})
export class AuditTaskPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskPopupService: AuditTaskPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditTaskPopupService
                    .open(AuditTaskDialogComponent as Component, params['id']);
            } else {
                this.auditTaskPopupService
                    .open(AuditTaskDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
