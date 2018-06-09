import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RecommendationAttribute } from './recommendation-attribute.model';
import { RecommendationAttributeService } from './recommendation-attribute.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-recommendation-attribute',
    templateUrl: './recommendation-attribute.component.html'
})
export class RecommendationAttributeComponent implements OnInit, OnDestroy {
recommendationAttributes: RecommendationAttribute[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private recommendationAttributeService: RecommendationAttributeService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.recommendationAttributeService.query().subscribe(
            (res: ResponseWrapper) => {
                this.recommendationAttributes = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRecommendationAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RecommendationAttribute) {
        return item.id;
    }
    registerChangeInRecommendationAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('recommendationAttributeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
