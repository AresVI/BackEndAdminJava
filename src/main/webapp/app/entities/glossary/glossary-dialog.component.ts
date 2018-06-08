import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { Glossary } from './glossary.model';
import { GlossaryPopupService } from './glossary-popup.service';
import { GlossaryService } from './glossary.service';

@Component({
    selector: 'jhi-glossary-dialog',
    templateUrl: './glossary-dialog.component.html'
})
export class GlossaryDialogComponent implements OnInit {

    glossary: Glossary;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private glossaryService: GlossaryService,
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
        if (this.glossary.id !== undefined) {
            this.subscribeToSaveResponse(
                this.glossaryService.update(this.glossary));
        } else {
            this.subscribeToSaveResponse(
                this.glossaryService.create(this.glossary));
        }
    }

    private subscribeToSaveResponse(result: Observable<Glossary>) {
        result.subscribe((res: Glossary) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Glossary) {
        this.eventManager.broadcast({ name: 'glossaryListModification', content: 'OK'});
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
    selector: 'jhi-glossary-popup',
    template: ''
})
export class GlossaryPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private glossaryPopupService: GlossaryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.glossaryPopupService
                    .open(GlossaryDialogComponent as Component, params['id']);
            } else {
                this.glossaryPopupService
                    .open(GlossaryDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
