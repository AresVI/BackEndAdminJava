import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Faqs } from './faqs.model';
import { FaqsPopupService } from './faqs-popup.service';
import { FaqsService } from './faqs.service';

@Component({
    selector: 'jhi-faqs-delete-dialog',
    templateUrl: './faqs-delete-dialog.component.html'
})
export class FaqsDeleteDialogComponent {

    faqs: Faqs;

    constructor(
        private faqsService: FaqsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.faqsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'faqsListModification',
                content: 'Deleted an faqs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-faqs-delete-popup',
    template: ''
})
export class FaqsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private faqsPopupService: FaqsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.faqsPopupService
                .open(FaqsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
