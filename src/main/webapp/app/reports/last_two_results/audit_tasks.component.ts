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
import {TraceabilityAuditService} from '../../entities/traceability-audit/traceability-audit.service';
import {ComparativeTaskRecommendation} from './comparative.model';

@Component({
    selector: 'jhi-report-compare-audit-tasks-detail',
    templateUrl: './audit_tasks.component.html'
})
export class AuditTasksComponent implements OnInit {

    currentAccount: Account;
    company: Company;
    auditProcess: AuditProcess;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    reviewDone: boolean;

    traceabilityAudits: TraceabilityAudit[];

    comparative: ComparativeTaskRecommendation[];

    constructor(
        private companyService: CompanyService,
        private auditProcessService: AuditProcessService,
        private traceabilityAuditService: TraceabilityAuditService,
        private principal: Principal,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute,
        private alertService: JhiAlertService
    ) {}

    ngOnInit() {

        this.reviewDone = false;

        this.comparative = [];

        this.traceabilityAudits = [];

        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id'], params['process_id']);
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }

    load(id, audit_process_id) {
        this.companyService.find(id).subscribe((company) => {
            this.company = company;
            this.auditProcessService.findComplete(audit_process_id).subscribe((auditProcess) => {
                this.auditProcess = auditProcess;
                this.getLastTwoTraceabilityAuditsResults(id);
            });
        });
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

    trackId(index: number, item: TraceabilityAudit) {
        return item.id;
    }

    private onSuccessLastTwoTraceabilityAudits(data: TraceabilityAudit[]) {

        this.traceabilityAuditService.find(data[0].id).subscribe((traceabilityAuditOld) => {
            this.traceabilityAudits.push(traceabilityAuditOld);

            this.traceabilityAuditService.find(data[1].id).subscribe((traceabilityAuditNew) => {
                this.traceabilityAudits.push(traceabilityAuditNew);
                this.compareTraceabilityAudits();
            });
        });
    }

    private onErrorLastTwoTraceabilityAudits(error) {
        this.alertService.error(error.message, null, null);
    }

    private compareTraceabilityAudits() {
        this.companyService.getComparativeLastTwoTraceabilityAuditsResults(this.company.id, this.auditProcess.id).subscribe(
            (res: ResponseWrapper) => this.onSuccessCompareTraceabilityAudits(res.json),
            (res: ResponseWrapper) => this.onErrorCompareTraceabilityAudits(res.json)
        );
    }

    private onSuccessCompareTraceabilityAudits(data: ComparativeTaskRecommendation[]) {
        this.comparative = data;
        this.reviewDone = true;
    }

    private onErrorCompareTraceabilityAudits(error) {
        this.alertService.error(error.message, null, null);
    }

}
