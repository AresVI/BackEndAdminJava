import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditService } from './traceability-audit.service';

@Component({
    selector: 'jhi-traceability-audit-detail',
    templateUrl: './traceability-audit-detail.component.html'
})
export class TraceabilityAuditDetailComponent implements OnInit, OnDestroy {

    traceabilityAudit: TraceabilityAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private traceabilityAuditService: TraceabilityAuditService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraceabilityAudits();
    }

    load(id) {
        this.traceabilityAuditService.find(id).subscribe((traceabilityAudit) => {
            this.traceabilityAudit = traceabilityAudit;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traceabilityAuditListModification',
            (response) => this.load(this.traceabilityAudit.id)
        );
    }
}
