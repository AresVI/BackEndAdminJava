import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Container } from './container.model';
import { ContainerPopupService } from './container-popup.service';
import { ContainerService } from './container.service';
import { Participant, ParticipantService } from '../participant';
import { AuditProcess, AuditProcessService } from '../audit-process';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-container-dialog',
    templateUrl: './container-dialog.component.html'
})
export class ContainerDialogComponent implements OnInit {

    container: Container;
    isSaving: boolean;

    participants: Participant[];

    auditprocesses: AuditProcess[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private containerService: ContainerService,
        private participantService: ParticipantService,
        private auditProcessService: AuditProcessService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.participantService
            .query({filter: 'container-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.container.participantId) {
                    this.participants = res.json;
                } else {
                    this.participantService
                        .find(this.container.participantId)
                        .subscribe((subRes: Participant) => {
                            this.participants = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.auditProcessService.query()
            .subscribe((res: ResponseWrapper) => { this.auditprocesses = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.container.id !== undefined) {
            this.subscribeToSaveResponse(
                this.containerService.update(this.container));
        } else {
            this.subscribeToSaveResponse(
                this.containerService.create(this.container));
        }
    }

    private subscribeToSaveResponse(result: Observable<Container>) {
        result.subscribe((res: Container) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Container) {
        this.eventManager.broadcast({ name: 'containerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackParticipantById(index: number, item: Participant) {
        return item.id;
    }

    trackAuditProcessById(index: number, item: AuditProcess) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-container-popup',
    template: ''
})
export class ContainerPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private containerPopupService: ContainerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.containerPopupService
                    .open(ContainerDialogComponent as Component, params['id']);
            } else {
                this.containerPopupService
                    .open(ContainerDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
