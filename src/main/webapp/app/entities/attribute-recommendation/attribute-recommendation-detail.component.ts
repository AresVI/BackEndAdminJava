import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { AttributeRecommendation } from './attribute-recommendation.model';
import { AttributeRecommendationService } from './attribute-recommendation.service';

@Component({
    selector: 'jhi-attribute-recommendation-detail',
    templateUrl: './attribute-recommendation-detail.component.html'
})
export class AttributeRecommendationDetailComponent implements OnInit, OnDestroy {

    attributeRecommendation: AttributeRecommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private attributeRecommendationService: AttributeRecommendationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAttributeRecommendations();
    }

    load(id) {
        this.attributeRecommendationService.find(id).subscribe((attributeRecommendation) => {
            this.attributeRecommendation = attributeRecommendation;
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

    registerChangeInAttributeRecommendations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'attributeRecommendationListModification',
            (response) => this.load(this.attributeRecommendation.id)
        );
    }
}
