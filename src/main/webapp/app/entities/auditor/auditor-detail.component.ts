import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Auditor } from './auditor.model';
import { AuditorService } from './auditor.service';

@Component({
    selector: 'jhi-auditor-detail',
    templateUrl: './auditor-detail.component.html'
})
export class AuditorDetailComponent implements OnInit, OnDestroy {

    auditor: Auditor;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private auditorService: AuditorService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAuditors();
    }

    load(id) {
        this.auditorService.find(id).subscribe((auditor) => {
            this.auditor = auditor;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAuditors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'auditorListModification',
            (response) => this.load(this.auditor.id)
        );
    }
}
