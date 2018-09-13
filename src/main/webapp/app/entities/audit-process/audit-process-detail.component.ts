import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AuditProcess } from './audit-process.model';
import { AuditProcessService } from './audit-process.service';
import { Container } from '../container';

@Component({
    selector: 'jhi-audit-process-detail',
    templateUrl: './audit-process-detail.component.html'
})
export class AuditProcessDetailComponent implements OnInit, OnDestroy {

    auditProcess: AuditProcess;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    public containers: any;

    constructor(
        private eventManager: JhiEventManager,
        private auditProcessService: AuditProcessService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditProcesses();
    }

    load(id) {
        this.auditProcessService.find(id).subscribe((auditProcess) => {
            this.auditProcess = auditProcess;
            this.auditProcessService.findContainers(id).subscribe((res) => {
                this.containers = res;
            });
        });
    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditProcesses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditProcessListModification',
            (response) => this.load(this.auditProcess.id)
        );
    }
}
