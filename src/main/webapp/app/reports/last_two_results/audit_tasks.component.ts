import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import {JhiAlertService, JhiEventManager} from 'ng-jhipster';

import {Principal} from '../../shared/auth/principal.service';
import {Company} from '../../entities/company/company.model';
import {TraceabilityAudit} from '../../entities/traceability-audit/traceability-audit.model';
import {CompanyService} from '../../entities/company/company.service';
import {AuditProcess} from '../../entities/audit-process/audit-process.model';
import {AuditProcessService} from '../../entities/audit-process/audit-process.service';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';

@Component({
    selector: 'jhi-company-detail',
    templateUrl: './audit_process.component.html'
})
export class AuditProcessComponent implements OnInit, OnDestroy {

    currentAccount: Account;
    company: Company;
    auditProcesses: AuditProcess[];
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    reviewDone: boolean;

    traceabilityAudits: TraceabilityAudit[];

    comparativeAuditProcessOld: boolean[];
    comparativeAuditProcessNew: boolean[];

    constructor(
        private companyService: CompanyService,
        private auditProcessService: AuditProcessService,
        private principal: Principal,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private alertService: JhiAlertService
    ) {}

    ngOnInit() {

        this.reviewDone = false;
        this.comparativeAuditProcessNew = [];
        this.comparativeAuditProcessOld = [];

        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCompanies();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }

    load(id) {
        this.getLastTwoTraceabilityAuditsResults(id);
        this.companyService.find(id).subscribe((company) => {
            this.company = company;
        });
    }

    loadAllAuditProcess() {
        this.auditProcessService.queryAll({}).subscribe(
            (res: ResponseWrapper) => this.onSuccessAuditProcess(res.json),
            (res: ResponseWrapper) => this.onErrorAuditProcess(res.json)
        );
    }

    getLastTwoTraceabilityAuditsResults(company_id: number) {
        this.companyService.getLastTwoTraceabilityAuditsResults(company_id).subscribe(
            (res: ResponseWrapper) => this.onSuccessLastTwoTraceabilityAudits(res.json),
            (res: ResponseWrapper) => this.onErrorLastTwoTraceabilityAudits(res.json)
        );
    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompanies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'companyListModification',
            (response) => this.load(this.company.id)
        );
    }

    trackId(index: number, item: TraceabilityAudit) {
        return item.id;
    }

    containsAuditProcess(traceabilityAudit: TraceabilityAudit, auditProcess: AuditProcess) {

        let result = false;

        traceabilityAudit.auditProcesses.forEach((item) => {
            if (item.id === auditProcess.id) {
                result = true;
            }
        });

        return result;

    }

    reviewTraceabilityAudits() {

        this.auditProcesses.forEach((auditProcess) => {
                this.comparativeAuditProcessNew.push(this.containsAuditProcess(this.traceabilityAudits[1], auditProcess));
                this.comparativeAuditProcessOld.push(this.containsAuditProcess(this.traceabilityAudits[0], auditProcess));
        });

        this.reviewDone = true;

    }

    private onSuccessAuditProcess(data) {
        this.auditProcesses = data;
        this.reviewTraceabilityAudits();
    }

    private onErrorAuditProcess(error) {
        this.alertService.error(error.message, null, null);
    }

    private onSuccessLastTwoTraceabilityAudits(data) {
        this.traceabilityAudits = data;
        this.loadAllAuditProcess();
    }

    private onErrorLastTwoTraceabilityAudits(error) {
        this.alertService.error(error.message, null, null);
    }

}
