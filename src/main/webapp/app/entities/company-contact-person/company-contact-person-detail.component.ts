import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyContactPerson } from './company-contact-person.model';
import { CompanyContactPersonPopupService } from './company-contact-person-popup.service';
import { CompanyContactPersonService } from './company-contact-person.service';
import { Company } from '../company';
import {Subscription} from 'rxjs/Subscription';

@Component({
    selector: 'jhi-company-contact-person-detail-dialog',
    templateUrl: './company-contact-person-detail.component.html'
})
export class CompanyContactPersonDetailComponent implements OnInit {

    company_contact_person: CompanyContactPerson;
    isSaving: boolean;

    companies: Company[];
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

    close() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.company_contact_person.id !== undefined) {
            this.subscribeToSaveResponse(
                this.company_contact_personService.update(this.company_id, this.company_contact_person));
        } else {
            this.subscribeToSaveResponse(
                this.company_contact_personService.create(this.company_id, this.company_contact_person));
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
    selector: 'jhi-company-contact-person-detail-popup',
    template: ''
})
export class CompanyContactPersonDetailPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private company_contact_personPopupService: CompanyContactPersonPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.company_contact_personPopupService
                    .open(CompanyContactPersonDetailComponent as Component, params['company_id'], params['id']);
            } else {
                this.company_contact_personPopupService
                    .open(CompanyContactPersonDetailComponent as Component, params['company_id']);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
