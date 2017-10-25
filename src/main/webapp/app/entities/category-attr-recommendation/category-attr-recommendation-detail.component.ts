import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttrRecommendation } from './category-attr-recommendation.model';
import { CategoryAttrRecommendationService } from './category-attr-recommendation.service';

@Component({
    selector: 'jhi-category-attr-recommendation-detail',
    templateUrl: './category-attr-recommendation-detail.component.html'
})
export class CategoryAttrRecommendationDetailComponent implements OnInit, OnDestroy {

    categoryAttrRecommendation: CategoryAttrRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private categoryAttrRecommendationService: CategoryAttrRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategoryAttrRecommendations();
    }

    load(id) {
        this.categoryAttrRecommendationService.find(id).subscribe((categoryAttrRecommendation) => {
            this.categoryAttrRecommendation = categoryAttrRecommendation;
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

    registerChangeInCategoryAttrRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryAttrRecommendationListModification',
            (response) => this.load(this.categoryAttrRecommendation.id)
        );
    }
}
