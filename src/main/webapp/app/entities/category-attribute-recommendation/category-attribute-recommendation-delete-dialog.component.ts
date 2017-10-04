import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryAttributeRecommendation } from './category-attribute-recommendation.model';
import { CategoryAttributeRecommendationPopupService } from './category-attribute-recommendation-popup.service';
import { CategoryAttributeRecommendationService } from './category-attribute-recommendation.service';

@Component({
    selector: 'jhi-category-attribute-recommendation-delete-dialog',
    templateUrl: './category-attribute-recommendation-delete-dialog.component.html'
})
export class CategoryAttributeRecommendationDeleteDialogComponent {

    categoryAttributeRecommendation: CategoryAttributeRecommendation;

    constructor(
        private categoryAttributeRecommendationService: CategoryAttributeRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryAttributeRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryAttributeRecommendationListModification',
                content: 'Deleted an categoryAttributeRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-attribute-recommendation-delete-popup',
    template: ''
})
export class CategoryAttributeRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttributeRecommendationPopupService: CategoryAttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryAttributeRecommendationPopupService
                .open(CategoryAttributeRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
