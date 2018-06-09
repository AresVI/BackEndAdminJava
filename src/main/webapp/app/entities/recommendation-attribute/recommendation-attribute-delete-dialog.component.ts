import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RecommendationAttribute } from './recommendation-attribute.model';
import { RecommendationAttributePopupService } from './recommendation-attribute-popup.service';
import { RecommendationAttributeService } from './recommendation-attribute.service';

@Component({
    selector: 'jhi-recommendation-attribute-delete-dialog',
    templateUrl: './recommendation-attribute-delete-dialog.component.html'
})
export class RecommendationAttributeDeleteDialogComponent {

    recommendationAttribute: RecommendationAttribute;

    constructor(
        private recommendationAttributeService: RecommendationAttributeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.recommendationAttributeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'recommendationAttributeListModification',
                content: 'Deleted an recommendationAttribute'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recommendation-attribute-delete-popup',
    template: ''
})
export class RecommendationAttributeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recommendationAttributePopupService: RecommendationAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.recommendationAttributePopupService
                .open(RecommendationAttributeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
