import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Glossary } from './glossary.model';
import { GlossaryPopupService } from './glossary-popup.service';
import { GlossaryService } from './glossary.service';

@Component({
    selector: 'jhi-glossary-delete-dialog',
    templateUrl: './glossary-delete-dialog.component.html'
})
export class GlossaryDeleteDialogComponent {

    glossary: Glossary;

    constructor(
        private glossaryService: GlossaryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.glossaryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'glossaryListModification',
                content: 'Deleted an glossary'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-glossary-delete-popup',
    template: ''
})
export class GlossaryDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private glossaryPopupService: GlossaryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.glossaryPopupService
                .open(GlossaryDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
