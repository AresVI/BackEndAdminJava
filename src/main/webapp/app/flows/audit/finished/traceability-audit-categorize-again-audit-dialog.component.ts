import { Component, OnInit, OnDestroy } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {TraceabilityAudit} from '../../../entities/traceability-audit/traceability-audit.model';
import {TraceabilityAuditService} from '../../../entities/traceability-audit/traceability-audit.service';
import {TraceabilityAuditPopupService} from '../../../entities/traceability-audit/traceability-audit-popup.service';

@Component({
    selector: 'jhi-traceability-audit-categorize-again-dialog',
    templateUrl: './traceability-audit-categorize-again-audit-dialog.component.html'
})
export class TraceabilityAuditCategorizeAgainAuditDialogComponent {

    traceabilityAudit: TraceabilityAudit;

    constructor(
        private traceabilityAuditService: TraceabilityAuditService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
        private router: Router,
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmCategorizeAgain(id: number) {
        this.traceabilityAuditService.categorizeAgain(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traceabilityAuditListModification',
                content: 'Categorize Again an traceabilityAudit'
            });

            this.router.navigate(['/process/audit/status/finished']).then(() => {
                this.activeModal.dismiss(true);
            });

        });
    }
}

@Component({
    selector: 'jhi-traceability-audit-categorize-again-popup',
    template: ''
})
export class TraceabilityAuditCategorizeAgainAuditPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traceabilityAuditPopupService: TraceabilityAuditPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traceabilityAuditPopupService
                .open(TraceabilityAuditCategorizeAgainAuditDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
