import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { RecommendationAttributeRecommendation } from './recommendation-attribute-recommendation.model';
import { RecommendationAttributeRecommendationService } from './recommendation-attribute-recommendation.service';

@Component({
    selector: 'jhi-recommendation-attribute-recommendation-detail',
    templateUrl: './recommendation-attribute-recommendation-detail.component.html'
})
export class RecommendationAttributeRecommendationDetailComponent implements OnInit, OnDestroy {

    recommendationAttributeRecommendation: RecommendationAttributeRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private recommendationAttributeRecommendationService: RecommendationAttributeRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRecommendationAttributeRecommendations();
    }

    load(id) {
        this.recommendationAttributeRecommendationService.find(id).subscribe((recommendationAttributeRecommendation) => {
            this.recommendationAttributeRecommendation = recommendationAttributeRecommendation;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRecommendationAttributeRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'recommendationAttributeRecommendationListModification',
            (response) => this.load(this.recommendationAttributeRecommendation.id)
        );
    }
}
