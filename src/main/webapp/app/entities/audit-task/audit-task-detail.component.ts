import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AuditTask } from './audit-task.model';
import { AuditTaskService } from './audit-task.service';

@Component({
    selector: 'jhi-audit-task-detail',
    templateUrl: './audit-task-detail.component.html'
})
export class AuditTaskDetailComponent implements OnInit, OnDestroy {

    auditTask: AuditTask;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private auditTaskService: AuditTaskService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditTasks();
    }

    load(id) {
        this.auditTaskService.find(id).subscribe((auditTask) => {
            this.auditTask = auditTask;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditTasks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditTaskListModification',
            (response) => this.load(this.auditTask.id)
        );
    }
}
