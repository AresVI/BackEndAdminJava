import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Recommendation } from './recommendation.model';
import { RecommendationPopupService } from './recommendation-popup.service';
import { RecommendationService } from './recommendation.service';

@Component({
    selector: 'jhi-recommendation-delete-dialog',
    templateUrl: './recommendation-delete-dialog.component.html'
})
export class RecommendationDeleteDialogComponent {

    recommendation: Recommendation;

    constructor(
        private recommendationService: RecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.recommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'recommendationListModification',
                content: 'Deleted an recommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recommendation-delete-popup',
    template: ''
})
export class RecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationPopupService: RecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.recommendationPopupService
                .open(RecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
