import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { AuditTaskRecommendationPopupService } from './audit-task-recommendation-popup.service';
import { AuditTaskRecommendationService } from './audit-task-recommendation.service';

@Component({
    selector: 'jhi-audit-task-recommendation-delete-dialog',
    templateUrl: './audit-task-recommendation-delete-dialog.component.html'
})
export class AuditTaskRecommendationDeleteDialogComponent {

    auditTaskRecommendation: AuditTaskRecommendation;

    constructor(
        private auditTaskRecommendationService: AuditTaskRecommendationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditTaskRecommendationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditTaskRecommendationListModification',
                content: 'Deleted an auditTaskRecommendation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-task-recommendation-delete-popup',
    template: ''
})
export class AuditTaskRecommendationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditTaskRecommendationPopupService: AuditTaskRecommendationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditTaskRecommendationPopupService
                .open(AuditTaskRecommendationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
