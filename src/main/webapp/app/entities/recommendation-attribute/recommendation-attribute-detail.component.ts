import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { RecommendationAttribute } from './recommendation-attribute.model';
import { RecommendationAttributeService } from './recommendation-attribute.service';

@Component({
    selector: 'jhi-recommendation-attribute-detail',
    templateUrl: './recommendation-attribute-detail.component.html'
})
export class RecommendationAttributeDetailComponent implements OnInit, OnDestroy {

    recommendationAttribute: RecommendationAttribute;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private recommendationAttributeService: RecommendationAttributeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRecommendationAttributes();
    }

    load(id) {
        this.recommendationAttributeService.find(id).subscribe((recommendationAttribute) => {
            this.recommendationAttribute = recommendationAttribute;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRecommendationAttributes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'recommendationAttributeListModification',
            (response) => this.load(this.recommendationAttribute.id)
        );
    }
}
