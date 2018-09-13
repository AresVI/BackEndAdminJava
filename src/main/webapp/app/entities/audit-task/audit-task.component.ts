import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AuditTask } from './audit-task.model';
import { AuditTaskService } from './audit-task.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-audit-tasks',
    templateUrl: './audit-task.component.html'
})
export class AuditTaskComponent implements OnInit, OnDestroy {

    @Input() container_id: number;

    currentAccount: any;
    auditTasks: AuditTask[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private auditTaskService: AuditTaskService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.auditTasks = [];
    }

    loadAll() {
        this.auditTaskService.findAuditTasksByContainerId(this.container_id).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    clear() {
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.registerChangeInAuditTasks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AuditTask) {
        return item.id;
    }
    registerChangeInAuditTasks() {
        this.eventSubscriber = this.eventManager.subscribe('auditTaskListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.auditTasks = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
