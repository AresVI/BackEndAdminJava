import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Faqs } from './faqs.model';
import { FaqsPopupService } from './faqs-popup.service';
import { FaqsService } from './faqs.service';

@Component({
    selector: 'jhi-faqs-dialog',
    templateUrl: './faqs-dialog.component.html'
})
export class FaqsDialogComponent implements OnInit {

    faqs: Faqs;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private faqsService: FaqsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.faqs.id !== undefined) {
            this.subscribeToSaveResponse(
                this.faqsService.update(this.faqs));
        } else {
            this.subscribeToSaveResponse(
                this.faqsService.create(this.faqs));
        }
    }

    private subscribeToSaveResponse(result: Observable<Faqs>) {
        result.subscribe((res: Faqs) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Faqs) {
        this.eventManager.broadcast({ name: 'faqsListModification', content: 'OK'});
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
    selector: 'jhi-faqs-popup',
    template: ''
})
export class FaqsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private faqsPopupService: FaqsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.faqsPopupService
                    .open(FaqsDialogComponent as Component, params['id']);
            } else {
                this.faqsPopupService
                    .open(FaqsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
