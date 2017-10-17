import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CancelationTraceabilityAudit } from './cancelation-traceability-audit.model';
import { CancelationTraceabilityAuditService } from './cancelation-traceability-audit.service';

@Component({
    selector: 'jhi-cancelation-traceability-audit-detail',
    templateUrl: './cancelation-traceability-audit-detail.component.html'
})
export class CancelationTraceabilityAuditDetailComponent implements OnInit, OnDestroy {

    cancelationTraceabilityAudit: CancelationTraceabilityAudit;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private cancelationTraceabilityAuditService: CancelationTraceabilityAuditService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCancelationTraceabilityAudits();
    }

    load(id) {
        this.cancelationTraceabilityAuditService.find(id).subscribe((cancelationTraceabilityAudit) => {
            this.cancelationTraceabilityAudit = cancelationTraceabilityAudit;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCancelationTraceabilityAudits() {
        this.eventSubscriber = this.eventManager.subscribe(
            'cancelationTraceabilityAuditListModification',
            (response) => this.load(this.cancelationTraceabilityAudit.id)
        );
    }
}
