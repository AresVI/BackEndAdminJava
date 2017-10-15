import {Component, OnInit} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import {Subscription} from "rxjs/Subscription";
import {Company} from "../../../entities/company/company.model";
import {JhiAlertService, JhiEventManager} from "ng-jhipster";
import {ActivatedRoute, Router} from "@angular/router";

import { Principal, ResponseWrapper } from '../../../shared';
import {TraceabilityAudit} from "../../../entities/traceability-audit/traceability-audit.model";
import {Auditor} from "../../../entities/auditor/auditor.model";
import {AuditorService} from "../../../entities/auditor/auditor.service";
import {CompanyService} from "../../../entities/company/company.service";
import {CompanyContactPersonService} from "../../../entities/company-contact-person/company-contact-person.service";
import {CompanyContactPerson} from "../../../entities/company-contact-person/company-contact-person.model";

@Component({
    selector: 'jhi-flow-audit-start',
    templateUrl: './start.component.html',
})
export class StartComponent implements OnInit{
    currentAccount: Account;
    modalRef: NgbModalRef;

    traceabilityAudit: TraceabilityAudit;
    companies: Company[];
    auditors: Auditor[];
    companyContactPeople: CompanyContactPerson[];
    error: any;
    success: any;
    eventSubscriberCompanies: Subscription;
    routeData: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    filter_company: string;
    filter_auditor: string;
    filter_company_contact_person: string;

    constructor(
        private company_contact_personService: CompanyContactPersonService,
        private companyService: CompanyService,
        private auditorService: AuditorService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {
        });
    }

    loadAllCompanies() {
        this.companyService.queryAll({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessCompanies(res.json),
            (res: ResponseWrapper) => this.onErrorCompanies(res.json)
        );
    }

    loadAllCompanyContactPeople() {
        if (this.traceabilityAudit.companyId){
            this.company_contact_personService.queryAll(this.traceabilityAudit.companyId,
                {}).subscribe(
                (res: ResponseWrapper) => this.onSuccessCompanyContactPeople(res.json),
                (res: ResponseWrapper) => this.onErrorCompanyContactPeople(res.json)
            );
        }
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.traceabilityAudit = new TraceabilityAudit();
        this.loadAllCompanies();
        this.loadAllCompanyContactPeople();
        this.filter_company = "";
        this.filter_auditor = "";
        this.filter_company_contact_person = "";
        this.companies = [];
        this.auditors = [];
        this.companyContactPeople = [];
        this.registerChangeInCompanies();
    }

    changeCompanyId() {
        this.loadAllCompanyContactPeople();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
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
}
