import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CategoryAttribute } from './category-attribute.model';
import { CategoryAttributePopupService } from './category-attribute-popup.service';
import { CategoryAttributeService } from './category-attribute.service';
import { AuditTask, AuditTaskService } from '../audit-task';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-category-attribute-dialog',
    templateUrl: './category-attribute-dialog.component.html'
})
export class CategoryAttributeDialogComponent implements OnInit {

    categoryAttribute: CategoryAttribute;
    isSaving: boolean;

    audittasks: AuditTask[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private categoryAttributeService: CategoryAttributeService,
        private auditTaskService: AuditTaskService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.auditTaskService.query()
            .subscribe((res: ResponseWrapper) => { this.audittasks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.categoryAttribute.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryAttributeService.update(this.categoryAttribute));
        } else {
            this.subscribeToSaveResponse(
                this.categoryAttributeService.create(this.categoryAttribute));
        }
    }

    private subscribeToSaveResponse(result: Observable<CategoryAttribute>) {
        result.subscribe((res: CategoryAttribute) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CategoryAttribute) {
        this.eventManager.broadcast({ name: 'categoryAttributeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackAuditTaskById(index: number, item: AuditTask) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-category-attribute-popup',
    template: ''
})
export class CategoryAttributePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttributePopupService: CategoryAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryAttributePopupService
                    .open(CategoryAttributeDialogComponent as Component, params['id']);
            } else {
                this.categoryAttributePopupService
                    .open(CategoryAttributeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
