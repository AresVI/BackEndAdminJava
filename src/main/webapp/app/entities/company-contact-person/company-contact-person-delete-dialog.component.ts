import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyContactPerson } from './company-contact-person.model';
import { CompanyContactPersonPopupService } from './company-contact-person-popup.service';
import { CompanyContactPersonService } from './company-contact-person.service';

@Component({
    selector: 'jhi-company-contact-person-delete-dialog',
    templateUrl: './company-contact-person-delete-dialog.component.html'
})
export class CompanyContactPersonDeleteDialogComponent {

    company_contact_person: CompanyContactPerson;

    constructor(
        private company_contact_personService: CompanyContactPersonService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.company_contact_personService.delete(this.company_contact_person.company.id, id)
            .subscribe((response) => {
                this.eventManager.broadcast({
                    name: 'company_contact_personListModification',
                    content: 'Deleted an company_contact_person'
                });
                this.activeModal.dismiss(true);
            });
    }
}

@Component({
    selector: 'jhi-company-contact-person-delete-popup',
    template: ''
})
export class CompanyContactPersonDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private company_contact_personPopupService: CompanyContactPersonPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.company_contact_personPopupService
                .open(CompanyContactPersonDeleteDialogComponent as Component, params['company_id'], params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
