import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Attribute } from './attribute.model';
import { AttributePopupService } from './attribute-popup.service';
import { AttributeService } from './attribute.service';
import { Weighting, WeightingService } from '../weighting';
import { CategoryAttribute, CategoryAttributeService } from '../category-attribute';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attribute-dialog',
    templateUrl: './attribute-dialog.component.html'
})
export class AttributeDialogComponent implements OnInit {

    attribute: Attribute;
    isSaving: boolean;

    weightings: Weighting[];

    categoryattributes: CategoryAttribute[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private attributeService: AttributeService,
        private weightingService: WeightingService,
        private categoryAttributeService: CategoryAttributeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.weightingService
            .query({filter: 'attribute-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.attribute.weightingId) {
                    this.weightings = res.json;
                } else {
                    this.weightingService
                        .find(this.attribute.weightingId)
                        .subscribe((subRes: Weighting) => {
                            this.weightings = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.categoryAttributeService.query()
            .subscribe((res: ResponseWrapper) => { this.categoryattributes = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.attribute.id !== undefined) {
            this.subscribeToSaveResponse(
                this.attributeService.update(this.attribute));
        } else {
            this.subscribeToSaveResponse(
                this.attributeService.create(this.attribute));
        }
    }

    private subscribeToSaveResponse(result: Observable<Attribute>) {
        result.subscribe((res: Attribute) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Attribute) {
        this.eventManager.broadcast({ name: 'attributeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackWeightingById(index: number, item: Weighting) {
        return item.id;
    }

    trackCategoryAttributeById(index: number, item: CategoryAttribute) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-attribute-popup',
    template: ''
})
export class AttributePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private attributePopupService: AttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.attributePopupService
                    .open(AttributeDialogComponent as Component, params['id']);
            } else {
                this.attributePopupService
                    .open(AttributeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
