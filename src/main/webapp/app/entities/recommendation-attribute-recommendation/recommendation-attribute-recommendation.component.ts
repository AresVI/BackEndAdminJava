import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RecommendationAttributeRecommendation } from './recommendation-attribute-recommendation.model';
import { RecommendationAttributeRecommendationService } from './recommendation-attribute-recommendation.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-recommendation-attribute-recommendation',
    templateUrl: './recommendation-attribute-recommendation.component.html'
})
export class RecommendationAttributeRecommendationComponent implements OnInit, OnDestroy {
recommendationAttributeRecommendations: RecommendationAttributeRecommendation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private recommendationAttributeRecommendationService: RecommendationAttributeRecommendationService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.recommendationAttributeRecommendationService.query().subscribe(
            (res: ResponseWrapper) => {
                this.recommendationAttributeRecommendations = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRecommendationAttributeRecommendations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RecommendationAttributeRecommendation) {
        return item.id;
    }
    registerChangeInRecommendationAttributeRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe('recommendationAttributeRecommendationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
