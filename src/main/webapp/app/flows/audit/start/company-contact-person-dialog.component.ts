import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyContactPersonAuditStartPopupService } from './company-contact-person-popup.service';
import {Subscription} from 'rxjs/Subscription';
import {CompanyContactPerson} from '../../../entities/company-contact-person/company-contact-person.model';
import {CompanyContactPersonService} from '../../../entities/company-contact-person/company-contact-person.service';

@Component({
    selector: 'jhi-company-contact-person-dialog',
    templateUrl: './company-contact-person-dialog.component.html'
})
export class CompanyContactPersonAuditStartDialogComponent implements OnInit {

    company_contact_person: CompanyContactPerson;
    isSaving: boolean;

    company_id: number;
    private subscription: Subscription;

    constructor(
        public activeModal: NgbActiveModal,
        private company_contact_personService: CompanyContactPersonService,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.company_id = params['company_id'];
        });
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;

        this.company_contact_person.companyId = this.company_contact_person.company.id;

        if (this.company_contact_person.id !== undefined) {
            this.subscribeToSaveResponse(
                this.company_contact_personService.update(this.company_contact_person.company.id, this.company_contact_person));
        } else {
            this.subscribeToSaveResponse(
                this.company_contact_personService.create(this.company_contact_person.company.id, this.company_contact_person));
        }
    }

    private subscribeToSaveResponse(result: Observable<CompanyContactPerson>) {
        result.subscribe((res: CompanyContactPerson) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CompanyContactPerson) {
        this.eventManager.broadcast({ name: 'company_contact_personListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-company-contact-person-popup',
    template: ''
})
export class CompanyContactPersonAuditStartPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private company_contact_personPopupService: CompanyContactPersonAuditStartPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.company_contact_personPopupService
                    .open(CompanyContactPersonAuditStartDialogComponent as Component, params['company_id'], params['id']);
            } else {
                this.company_contact_personPopupService
                    .open(CompanyContactPersonAuditStartDialogComponent as Component, params['company_id']);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
