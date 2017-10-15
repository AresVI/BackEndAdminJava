import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyContactPerson } from './company-contact-person.model';
import { CompanyContactPersonService } from './company-contact-person.service';

@Component({
    selector: 'jhi-company-contact-person-detail',
    templateUrl: './company-contact-person-detail.component.html'
})
export class CompanyContactPersonDetailComponent implements OnInit, OnDestroy {

    company_contact_person: CompanyContactPerson;
    company_id: number;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private company_contact_personService: CompanyContactPersonService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.company_id = params['company_id'];
            this.load(params['id']);
        });
        this.registerChangeInCompany_contact_people();
    }

    load(id) {
        this.company_contact_personService.find(this.company_id, id).subscribe((company_contact_person) => {
            this.company_contact_person = company_contact_person;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompany_contact_people() {
        this.eventSubscriber = this.eventManager.subscribe(
            'company_contact_personListModification',
            (response) => this.load(this.company_contact_person.id)
        );
    }
}
