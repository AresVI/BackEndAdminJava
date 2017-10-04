import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Recommendation } from './recommendation.model';
import { RecommendationService } from './recommendation.service';

@Component({
    selector: 'jhi-recommendation-detail',
    templateUrl: './recommendation-detail.component.html'
})
export class RecommendationDetailComponent implements OnInit, OnDestroy {

    recommendation: Recommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private recommendationService: RecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRecommendations();
    }

    load(id) {
        this.recommendationService.find(id).subscribe((recommendation) => {
            this.recommendation = recommendation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'recommendationListModification',
            (response) => this.load(this.recommendation.id)
        );
    }
}
