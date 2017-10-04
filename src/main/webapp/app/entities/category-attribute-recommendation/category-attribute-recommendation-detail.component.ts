import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttributeRecommendation } from './category-attribute-recommendation.model';
import { CategoryAttributeRecommendationService } from './category-attribute-recommendation.service';

@Component({
    selector: 'jhi-category-attribute-recommendation-detail',
    templateUrl: './category-attribute-recommendation-detail.component.html'
})
export class CategoryAttributeRecommendationDetailComponent implements OnInit, OnDestroy {

    categoryAttributeRecommendation: CategoryAttributeRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private categoryAttributeRecommendationService: CategoryAttributeRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategoryAttributeRecommendations();
    }

    load(id) {
        this.categoryAttributeRecommendationService.find(id).subscribe((categoryAttributeRecommendation) => {
            this.categoryAttributeRecommendation = categoryAttributeRecommendation;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCategoryAttributeRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryAttributeRecommendationListModification',
            (response) => this.load(this.categoryAttributeRecommendation.id)
        );
    }
}
