import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Auditor } from './auditor.model';
import { AuditorPopupService } from './auditor-popup.service';
import { AuditorService } from './auditor.service';
import { User, UserService } from '../../shared';
import { Company, CompanyService } from '../company';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-auditor-dialog',
    templateUrl: './auditor-dialog.component.html'
})
export class AuditorDialogComponent implements OnInit {

    auditor: Auditor;
    isSaving: boolean;

    users: User[];

    companies: Company[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private auditorService: AuditorService,
        private userService: UserService,
        private companyService: CompanyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.queryNoRole()
            .subscribe((res: ResponseWrapper) => { this.users = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.companyService.query()
            .subscribe((res: ResponseWrapper) => { this.companies = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.auditor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.auditorService.update(this.auditor));
        } else {
            this.subscribeToSaveResponse(
                this.auditorService.create(this.auditor));
        }
    }

    private subscribeToSaveResponse(result: Observable<Auditor>) {
        result.subscribe((res: Auditor) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Auditor) {
        this.eventManager.broadcast({ name: 'auditorListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-auditor-popup',
    template: ''
})
export class AuditorPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditorPopupService: AuditorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.auditorPopupService
                    .open(AuditorDialogComponent as Component, params['id']);
            } else {
                this.auditorPopupService
                    .open(AuditorDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
