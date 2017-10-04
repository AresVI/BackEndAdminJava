import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AttributeRecommendation } from './attribute-recommendation.model';
import { AttributeRecommendationPopupService } from './attribute-recommendation-popup.service';
import { AttributeRecommendationService } from './attribute-recommendation.service';

@Component({
    selector: 'jhi-attribute-recommendation-delete-dialog',
    templateUrl: './attribute-recommendation-delete-dialog.component.html'
})
export class AttributeRecommendationDeleteDialogComponent {

    attributeRecommendation: AttributeRecommendation;

    constructor(
        private attributeRecommendationService: AttributeRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attributeRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'attributeRecommendationListModification',
                content: 'Deleted an attributeRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attribute-recommendation-delete-popup',
    template: ''
})
export class AttributeRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attributeRecommendationPopupService: AttributeRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.attributeRecommendationPopupService
                .open(AttributeRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
