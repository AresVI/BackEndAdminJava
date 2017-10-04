import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditProcessRecommendation } from './audit-process-recommendation.model';
import { AuditProcessRecommendationPopupService } from './audit-process-recommendation-popup.service';
import { AuditProcessRecommendationService } from './audit-process-recommendation.service';

@Component({
    selector: 'jhi-audit-process-recommendation-delete-dialog',
    templateUrl: './audit-process-recommendation-delete-dialog.component.html'
})
export class AuditProcessRecommendationDeleteDialogComponent {

    auditProcessRecommendation: AuditProcessRecommendation;

    constructor(
        private auditProcessRecommendationService: AuditProcessRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditProcessRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditProcessRecommendationListModification',
                content: 'Deleted an auditProcessRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-process-recommendation-delete-popup',
    template: ''
})
export class AuditProcessRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditProcessRecommendationPopupService: AuditProcessRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditProcessRecommendationPopupService
                .open(AuditProcessRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
