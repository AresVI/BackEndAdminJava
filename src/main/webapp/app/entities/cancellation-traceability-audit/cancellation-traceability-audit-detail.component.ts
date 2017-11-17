import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CancellationTraceabilityAudit } from './cancellation-traceability-audit.model';
import { CancellationTraceabilityAuditService } from './cancellation-traceability-audit.service';

@Component({
    selector: 'jhi-cancellation-traceability-audit-detail',
    templateUrl: './cancellation-traceability-audit-detail.component.html'
})
export class CancellationTraceabilityAuditDetailComponent implements OnInit, OnDestroy {

    cancellationTraceabilityAudit: CancellationTraceabilityAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cancellationTraceabilityAuditService: CancellationTraceabilityAuditService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCancellationTraceabilityAudits();
    }

    load(id) {
        this.cancellationTraceabilityAuditService.find(id).subscribe((cancellationTraceabilityAudit) => {
            this.cancellationTraceabilityAudit = cancellationTraceabilityAudit;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCancellationTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cancellationTraceabilityAuditListModification',
            (response) => this.load(this.cancellationTraceabilityAudit.id)
        );
    }
}
