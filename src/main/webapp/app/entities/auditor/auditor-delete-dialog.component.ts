import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Auditor } from './auditor.model';
import { AuditorPopupService } from './auditor-popup.service';
import { AuditorService } from './auditor.service';

@Component({
    selector: 'jhi-auditor-delete-dialog',
    templateUrl: './auditor-delete-dialog.component.html'
})
export class AuditorDeleteDialogComponent {

    auditor: Auditor;

    constructor(
        private auditorService: AuditorService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.auditorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'auditorListModification',
                content: 'Deleted an auditor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-auditor-delete-popup',
    template: ''
})
export class AuditorDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private auditorPopupService: AuditorPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.auditorPopupService
                .open(AuditorDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
