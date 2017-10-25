import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryAttrRecommendation } from './category-attr-recommendation.model';
import { CategoryAttrRecommendationPopupService } from './category-attr-recommendation-popup.service';
import { CategoryAttrRecommendationService } from './category-attr-recommendation.service';

@Component({
    selector: 'jhi-category-attr-recommendation-delete-dialog',
    templateUrl: './category-attr-recommendation-delete-dialog.component.html'
})
export class CategoryAttrRecommendationDeleteDialogComponent {

    categoryAttrRecommendation: CategoryAttrRecommendation;

    constructor(
        private categoryAttrRecommendationService: CategoryAttrRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryAttrRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryAttrRecommendationListModification',
                content: 'Deleted an categoryAttrRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-attr-recommendation-delete-popup',
    template: ''
})
export class CategoryAttrRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttrRecommendationPopupService: CategoryAttrRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryAttrRecommendationPopupService
                .open(CategoryAttrRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
