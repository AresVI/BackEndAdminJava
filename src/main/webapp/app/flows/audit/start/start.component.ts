import {Component, OnInit} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import {Subscription} from 'rxjs/Subscription';
import {Company} from '../../../entities/company/company.model';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';
import {ActivatedRoute} from '@angular/router';

import { Principal, ResponseWrapper } from '../../../shared';
import {TraceabilityAudit} from '../../../entities/traceability-audit/traceability-audit.model';
import {CompanyService} from '../../../entities/company/company.service';
import {CompanyContactPersonService} from '../../../entities/company-contact-person/company-contact-person.service';
import {CompanyContactPerson} from '../../../entities/company-contact-person/company-contact-person.model';
import {Observable} from 'rxjs/Observable';
import {TraceabilityAuditService} from './traceability-audit.service';
import {isUndefined} from 'util';

@Component({
    selector: 'jhi-flow-audit-start',
    templateUrl: './start.component.html',
})

export class StartComponent implements OnInit {
    currentAccount: Account;
    modalRef: NgbModalRef;

    traceabilityAudit: TraceabilityAudit;
    companies: Company[];
    companyContactPeople: CompanyContactPerson[];
    isSaving: boolean;
    error: any;
    success: any;
    eventSubscriberCompanies: Subscription;
    eventSubscriberCompanyContactPeoples: Subscription;
    routeData: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    filter_company: string;

    constructor(
        private company_contact_personService: CompanyContactPersonService,
        private traceabilityAuditService: TraceabilityAuditService,
        private companyService: CompanyService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private eventManager: JhiEventManager,
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {

        });
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.traceabilityAudit = new TraceabilityAudit();
        this.loadAllCompanies();
        this.filter_company = '';
        this.companies = [];
        this.companyContactPeople = [];
        this.registerChangeInCompanies();
        this.registerChangeInCompanyContactPeople();
        this.isSaving = false;
    }

    loadAllCompanies() {
        this.companyService.queryAll({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessCompanies(res.json),
            (res: ResponseWrapper) => this.onErrorCompanies(res.json)
        );
    }

    loadAllCompanyContactPeople() {
        if (!isUndefined(this.traceabilityAudit.companyId)) {
            this.company_contact_personService.queryAll(this.traceabilityAudit.companyId,
                {}).subscribe(
                (res: ResponseWrapper) => this.onSuccessCompanyContactPeople(res.json),
                (res: ResponseWrapper) => this.onErrorCompanyContactPeople(res.json)
            );
        } else {
            this.companyContactPeople = [];
        }
    }

    changeCompanyId() {

        this.loadAllCompanyContactPeople();

        this.traceabilityAudit.companyContactPersonId = null;

    }

    changeCompanyContactPeopleId() {

    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    save() {
        this.traceabilityAudit.name = '';

        this.subscribeToSaveResponse(this.traceabilityAuditService.create(this.traceabilityAudit));
    }

    private subscribeToSaveResponse(result: Observable<TraceabilityAudit>) {
        result.subscribe((res: TraceabilityAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TraceabilityAudit) {
        this.eventManager.broadcast({ name: 'traceabilityAuditListModification', content: 'OK'});
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onSuccessCompanies(data) {
        this.companies = data;
    }
    private onErrorCompanies(error) {
        this.alertService.error(error.message, null, null);
    }

    private onSuccessCompanyContactPeople(data) {
        this.companyContactPeople = data;
    }
    private onErrorCompanyContactPeople(error) {
        this.alertService.error(error.message, null, null);
    }

    registerChangeInCompanies() {
        this.eventSubscriberCompanies = this.eventManager.subscribe('companyListModification', (response) => this.loadAllCompanies());
        this.eventSubscriberCompanies = this.eventManager.subscribe('companyListModification', (response) => this.loadAllCompanyContactPeople());
    }

    registerChangeInCompanyContactPeople() {
        this.eventSubscriberCompanyContactPeoples = this.eventManager.subscribe(
            'company_contact_personListModification',
            (response) => this.loadAllCompanyContactPeople()
        );
    }
}
