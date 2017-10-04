import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Weighting } from './weighting.model';
import { WeightingService } from './weighting.service';

@Component({
    selector: 'jhi-weighting-detail',
    templateUrl: './weighting-detail.component.html'
})
export class WeightingDetailComponent implements OnInit, OnDestroy {

    weighting: Weighting;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private weightingService: WeightingService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWeightings();
    }

    load(id) {
        this.weightingService.find(id).subscribe((weighting) => {
            this.weighting = weighting;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWeightings() {
        this.eventSubscriber = this.eventManager.subscribe(
            'weightingListModification',
            (response) => this.load(this.weighting.id)
        );
    }
}
