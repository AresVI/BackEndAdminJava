import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CompanyAddress } from './company-address.model';
import { CompanyAddressPopupService } from './company-address-popup.service';
import { CompanyAddressService } from './company-address.service';
import { Company, CompanyService } from '../company';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-company-address-dialog',
    templateUrl: './company-address-dialog.component.html'
})
export class CompanyAddressDialogComponent implements OnInit {

    companyAddress: CompanyAddress;
    isSaving: boolean;

    companies: Company[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private companyAddressService: CompanyAddressService,
        private companyService: CompanyService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService
            .query({filter: 'companyaddress-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.companyAddress.companyId) {
                    this.companies = res.json;
                } else {
                    this.companyService
                        .find(this.companyAddress.companyId)
                        .subscribe((subRes: Company) => {
                            this.companies = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.companyAddress.id !== undefined) {
            this.subscribeToSaveResponse(
                this.companyAddressService.update(this.companyAddress));
        } else {
            this.subscribeToSaveResponse(
                this.companyAddressService.create(this.companyAddress));
        }
    }

    private subscribeToSaveResponse(result: Observable<CompanyAddress>) {
        result.subscribe((res: CompanyAddress) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CompanyAddress) {
        this.eventManager.broadcast({ name: 'companyAddressListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-company-address-popup',
    template: ''
})
export class CompanyAddressPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private companyAddressPopupService: CompanyAddressPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.companyAddressPopupService
                    .open(CompanyAddressDialogComponent as Component, params['id']);
            } else {
                this.companyAddressPopupService
                    .open(CompanyAddressDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
