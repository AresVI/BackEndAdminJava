import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CategoryAttRecommendation } from './category-att-recommendation.model';
import { CategoryAttRecommendationService } from './category-att-recommendation.service';

@Component({
    selector: 'jhi-category-att-recommendation-detail',
    templateUrl: './category-att-recommendation-detail.component.html'
})
export class CategoryAttRecommendationDetailComponent implements OnInit, OnDestroy {

    categoryAttRecommendation: CategoryAttRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private categoryAttRecommendationService: CategoryAttRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategoryAttRecommendations();
    }

    load(id) {
        this.categoryAttRecommendationService.find(id).subscribe((categoryAttRecommendation) => {
            this.categoryAttRecommendation = categoryAttRecommendation;
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

    registerChangeInCategoryAttRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryAttRecommendationListModification',
            (response) => this.load(this.categoryAttRecommendation.id)
        );
    }
}
