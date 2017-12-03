import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AuditProcessStandardObservation } from './audit-process-standard-observation.model';
import { AuditProcessStandardObservationService } from './audit-process-standard-observation.service';

@Component({
    selector: 'jhi-audit-process-standard-observation-detail',
    templateUrl: './audit-process-standard-observation-detail.component.html'
})
export class AuditProcessStandardObservationDetailComponent implements OnInit, OnDestroy {

    auditProcessStandardObservation: AuditProcessStandardObservation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private auditProcessStandardObservationService: AuditProcessStandardObservationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditProcessStandardObservations();
    }

    load(id) {
        this.auditProcessStandardObservationService.find(id).subscribe((auditProcessStandardObservation) => {
            this.auditProcessStandardObservation = auditProcessStandardObservation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditProcessStandardObservations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditProcessStandardObservationListModification',
            (response) => this.load(this.auditProcessStandardObservation.id)
        );
    }
}
