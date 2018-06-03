import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TraceAudit } from './trace-audit.model';
import { TraceAuditService } from './trace-audit.service';

@Component({
    selector: 'jhi-trace-audit-detail',
    templateUrl: './trace-audit-detail.component.html'
})
export class TraceAuditDetailComponent implements OnInit, OnDestroy {

    traceAudit: TraceAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private traceAuditService: TraceAuditService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraceAudits();
    }

    load(id) {
        this.traceAuditService.find(id).subscribe((traceAudit) => {
            this.traceAudit = traceAudit;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTraceAudits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traceAuditListModification',
            (response) => this.load(this.traceAudit.id)
        );
    }
}
