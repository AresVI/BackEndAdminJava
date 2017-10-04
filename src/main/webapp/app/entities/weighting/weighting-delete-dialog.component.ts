import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Weighting } from './weighting.model';
import { WeightingPopupService } from './weighting-popup.service';
import { WeightingService } from './weighting.service';

@Component({
    selector: 'jhi-weighting-delete-dialog',
    templateUrl: './weighting-delete-dialog.component.html'
})
export class WeightingDeleteDialogComponent {

    weighting: Weighting;

    constructor(
        private weightingService: WeightingService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.weightingService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'weightingListModification',
                content: 'Deleted an weighting'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-weighting-delete-popup',
    template: ''
})
export class WeightingDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private weightingPopupService: WeightingPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.weightingPopupService
                .open(WeightingDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
