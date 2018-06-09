import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RecommendationAttributeRecommendation } from './recommendation-attribute-recommendation.model';
import { RecommendationAttributeRecommendationPopupService } from './recommendation-attribute-recommendation-popup.service';
import { RecommendationAttributeRecommendationService } from './recommendation-attribute-recommendation.service';

@Component({
    selector: 'jhi-recommendation-attribute-recommendation-delete-dialog',
    templateUrl: './recommendation-attribute-recommendation-delete-dialog.component.html'
})
export class RecommendationAttributeRecommendationDeleteDialogComponent {

    recommendationAttributeRecommendation: RecommendationAttributeRecommendation;

    constructor(
        private recommendationAttributeRecommendationService: RecommendationAttributeRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.recommendationAttributeRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'recommendationAttributeRecommendationListModification',
                content: 'Deleted an recommendationAttributeRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recommendation-attribute-recommendation-delete-popup',
    template: ''
})
export class RecommendationAttributeRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationAttributeRecommendationPopupService: RecommendationAttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.recommendationAttributeRecommendationPopupService
                .open(RecommendationAttributeRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
