import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryAttRecommendation } from './category-att-recommendation.model';
import { CategoryAttRecommendationPopupService } from './category-att-recommendation-popup.service';
import { CategoryAttRecommendationService } from './category-att-recommendation.service';

@Component({
    selector: 'jhi-category-att-recommendation-delete-dialog',
    templateUrl: './category-att-recommendation-delete-dialog.component.html'
})
export class CategoryAttRecommendationDeleteDialogComponent {

    categoryAttRecommendation: CategoryAttRecommendation;

    constructor(
        private categoryAttRecommendationService: CategoryAttRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryAttRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryAttRecommendationListModification',
                content: 'Deleted an categoryAttRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-att-recommendation-delete-popup',
    template: ''
})
export class CategoryAttRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttRecommendationPopupService: CategoryAttRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryAttRecommendationPopupService
                .open(CategoryAttRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
