import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';
import {Location} from '@angular/common';

import { Recommendation } from './recommendation.model';
import { RecommendationService } from './recommendation.service';
import {Observable} from 'rxjs/Observable';
import {AuditProcessRecommendation} from '../audit-process-recommendation/audit-process-recommendation.model';

@Component({
    selector: 'jhi-recommendation-detail',
    templateUrl: './recommendation-detail.component.html'
})
export class RecommendationDetailComponent implements OnInit, OnDestroy {

    recommendation: Recommendation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;
    isSaving: boolean;
    allProcessReviewed: boolean;
    hostname;

    constructor(
        private eventManager: JhiEventManager,
        private recommendationService: RecommendationService,
        private route: ActivatedRoute,
        private router: Router,
        private location: Location
    ) {
    }

    ngOnInit() {
        this.hostname = location.hostname;
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRecommendations();
    }

    load(id) {
        this.recommendationService.find(id).subscribe((recommendation) => {
            this.recommendation = recommendation;
            this.checkProcessReview();
        });
    }

    checkProcessReview() {

        let countProcessReviewed = 0;

        for (let aprIndex = 0; aprIndex < this.recommendation.auditProcessRecommendationSet.length; aprIndex++) {

            const apr: AuditProcessRecommendation = this.recommendation.auditProcessRecommendationSet[aprIndex];

            if (apr.reviewed) {
                countProcessReviewed += 1;
            }

        }

        if ( this.recommendation.auditProcessRecommendationSet.length === countProcessReviewed ) {
            this.allProcessReviewed = true;
        }

    }

    save() {
        this.isSaving = true;
        this.allProcessReviewed = false;

        this.subscribeToSaveResponse(
            this.recommendationService.update(this.recommendation));
    }

    private subscribeToSaveResponse(result: Observable<Recommendation>) {
        result.subscribe((res: Recommendation) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Recommendation) {
        this.eventManager.broadcast({ name: 'recommendationListModification', content: 'OK'});
        this.isSaving = false;
        this.router.navigate(['/traceability-audit', this.recommendation.traceabilityAuditId]);
    }

    private onSaveError() {
        this.isSaving = false;
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
