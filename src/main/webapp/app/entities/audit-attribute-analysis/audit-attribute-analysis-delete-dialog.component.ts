import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { AuditAttributeAnalysisPopupService } from './audit-attribute-analysis-popup.service';
import { AuditAttributeAnalysisService } from './audit-attribute-analysis.service';

@Component({
    selector: 'jhi-audit-attribute-analysis-delete-dialog',
    templateUrl: './audit-attribute-analysis-delete-dialog.component.html'
})
export class AuditAttributeAnalysisDeleteDialogComponent {

    auditAttributeAnalysis: AuditAttributeAnalysis;

    constructor(
        private auditAttributeAnalysisService: AuditAttributeAnalysisService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditAttributeAnalysisService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditAttributeAnalysisListModification',
                content: 'Deleted an auditAttributeAnalysis'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-audit-attribute-analysis-delete-popup',
    template: ''
})
export class AuditAttributeAnalysisDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditAttributeAnalysisPopupService: AuditAttributeAnalysisPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditAttributeAnalysisPopupService
                .open(AuditAttributeAnalysisDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
