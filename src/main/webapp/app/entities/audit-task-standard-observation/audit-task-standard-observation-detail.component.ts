import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AuditTaskStandardObservation } from './audit-task-standard-observation.model';
import { AuditTaskStandardObservationService } from './audit-task-standard-observation.service';

@Component({
    selector: 'jhi-audit-task-standard-observation-detail',
    templateUrl: './audit-task-standard-observation-detail.component.html'
})
export class AuditTaskStandardObservationDetailComponent implements OnInit, OnDestroy {

    auditTaskStandardObservation: AuditTaskStandardObservation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private auditTaskStandardObservationService: AuditTaskStandardObservationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditTaskStandardObservations();
    }

    load(id) {
        this.auditTaskStandardObservationService.find(id).subscribe((auditTaskStandardObservation) => {
            this.auditTaskStandardObservation = auditTaskStandardObservation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditTaskStandardObservations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditTaskStandardObservationListModification',
            (response) => this.load(this.auditTaskStandardObservation.id)
        );
    }
}
