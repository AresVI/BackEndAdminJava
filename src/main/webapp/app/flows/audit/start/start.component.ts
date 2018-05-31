
import {Component, OnInit} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import {Subscription} from 'rxjs/Subscription';
import {Company} from '../../../entities/company/company.model';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import { Principal, ResponseWrapper } from '../../../shared';
import {TraceabilityAudit} from '../../../entities/traceability-audit/traceability-audit.model';
import {CompanyService} from '../../../entities/company/company.service';
import {CompanyContactPersonService} from '../../../entities/company-contact-person/company-contact-person.service';
import {CompanyContactPerson} from '../../../entities/company-contact-person/company-contact-person.model';
import {Observable} from 'rxjs/Observable';
import {TraceabilityAuditService} from './traceability-audit.service';
import {isUndefined} from 'util';
import {AuditProcess} from '../../../entities/audit-process/audit-process.model';
import {AuditProcessService} from '../../../entities/audit-process/audit-process.service';
import { ActivatedRoute, Router } from '@angular/router';
import {ProductTypeService} from '../../../entities/product-type/product-type.service';
import {ProductType} from '../../../entities/product-type/product-type.model';

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
    productTypes: ProductType[];
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
    auditProcesses: AuditProcess[];

    constructor(
        private company_contact_personService: CompanyContactPersonService,
        private traceabilityAuditService: TraceabilityAuditService,
        private companyService: CompanyService,
        private auditProcessService: AuditProcessService,
        private productTypeService: ProductTypeService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private eventManager: JhiEventManager,
        private router: Router,
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {

        });
    }

    ngOnInit() {
        this.traceabilityAudit = new TraceabilityAudit();
        this.traceabilityAudit.companyId = 0;
        this.traceabilityAudit.productType = null;
        this.traceabilityAudit.companyContactPerson = null;
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.loadAllCompanies();
        this.filter_company = '';
        this.companies = [];
        this.companyContactPeople = [];
        this.productTypes = [];
        this.registerChangeInCompanies();
        this.registerChangeInCompanyContactPeople();
        // this.loadAllAuditProcess();
        this.loadAllProductTypes();
        this.isSaving = false;
    }

    loadAllCompanies() {
        this.companyService.queryAll({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessCompanies(res.json),
            (res: ResponseWrapper) => this.onErrorCompanies(res.json)
        );
    }

    loadAllCompanyContactPeople() {
        if (!isUndefined(this.traceabilityAudit.company.id)) {
            this.company_contact_personService.queryAll(this.traceabilityAudit.company.id,
                {}).subscribe(
                (res: ResponseWrapper) => this.onSuccessCompanyContactPeople(res.json),
                (res: ResponseWrapper) => this.onErrorCompanyContactPeople(res.json)
            );
        } else {
            this.companyContactPeople = [];
        }
    }

    loadAllAuditProcess() {

        this.auditProcessService.query({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessAuditProcess(res.json),
            (res: ResponseWrapper) => this.onErrorAuditProcess(res.json)
        );

    }

    loadAllProductTypes() {
        this.productTypeService.query({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessProductTypes(res.json),
            (res: ResponseWrapper) => this.onErrorProductTypes(res.json)
        );
    }

    changeCompanyId() {
        this.traceabilityAudit.companyId = this.traceabilityAudit.company.id;

        this.loadAllCompanyContactPeople();

        this.traceabilityAudit.companyContactPerson = null;
    }

    changeCompanyContactPeopleId() {
        this.traceabilityAudit.companyContactPersonId = this.traceabilityAudit.companyContactPerson.id;
    }

    changeProductType() {
        if (this.traceabilityAudit.productType) {
            this.auditProcesses = this.traceabilityAudit.productType.auditProcesses;
        } else {
            this.traceabilityAudit.auditProcesses = [];
            this.auditProcesses = [];
        }
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    save() {

        this.isSaving = true;

        this.traceabilityAudit.name = 'Auditoría ' + this.traceabilityAudit.company.name;

        this.traceabilityAudit.companyId = this.traceabilityAudit.company.id;

        this.subscribeToSaveResponse(this.traceabilityAuditService.create(this.traceabilityAudit));
    }

    private subscribeToSaveResponse(result: Observable<TraceabilityAudit>) {
        result.subscribe((res: TraceabilityAudit) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TraceabilityAudit) {
        this.eventManager.broadcast({ name: 'traceabilityAuditListModification', content: 'OK'});
        this.alertService.clear();
        this.alertService.success(
            'aresViApp.traceabilityAudit.created',
            { param : this.traceabilityAudit.company.name },
            null
        );

        this.isSaving = false;
        this.router.navigate(['/process/audit/dashboard']);
    }

    private onSaveError() {
        this.isSaving = false;
        this.alertService.error('Ocurrió un error al guardar.', null, null);
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

    private onSuccessAuditProcess(data) {
        this.auditProcesses = data;
    }
    private onErrorAuditProcess(error) {
        this.alertService.error(error.message, null, null);
    }

    private onSuccessProductTypes(data) {
        this.productTypes = data;
    }

    private onErrorProductTypes(error) {
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

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackAuditProcessById(index: number, item: AuditProcess) {
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
