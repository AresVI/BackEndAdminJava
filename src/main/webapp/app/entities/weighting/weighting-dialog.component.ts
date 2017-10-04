import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Weighting } from './weighting.model';
import { WeightingPopupService } from './weighting-popup.service';
import { WeightingService } from './weighting.service';

@Component({
    selector: 'jhi-weighting-dialog',
    templateUrl: './weighting-dialog.component.html'
})
export class WeightingDialogComponent implements OnInit {

    weighting: Weighting;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private weightingService: WeightingService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.weighting.id !== undefined) {
            this.subscribeToSaveResponse(
                this.weightingService.update(this.weighting));
        } else {
            this.subscribeToSaveResponse(
                this.weightingService.create(this.weighting));
        }
    }

    private subscribeToSaveResponse(result: Observable<Weighting>) {
        result.subscribe((res: Weighting) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Weighting) {
        this.eventManager.broadcast({ name: 'weightingListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-weighting-popup',
    template: ''
})
export class WeightingPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private weightingPopupService: WeightingPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.weightingPopupService
                    .open(WeightingDialogComponent as Component, params['id']);
            } else {
                this.weightingPopupService
                    .open(WeightingDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
