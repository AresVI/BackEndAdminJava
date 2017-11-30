import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyAddress } from './company-address.model';
import { CompanyAddressPopupService } from './company-address-popup.service';
import { CompanyAddressService } from './company-address.service';

@Component({
    selector: 'jhi-company-address-delete-dialog',
    templateUrl: './company-address-delete-dialog.component.html'
})
export class CompanyAddressDeleteDialogComponent {

    companyAddress: CompanyAddress;

    constructor(
        private companyAddressService: CompanyAddressService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.companyAddressService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'companyAddressListModification',
                content: 'Deleted an companyAddress'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-company-address-delete-popup',
    template: ''
})
export class CompanyAddressDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private companyAddressPopupService: CompanyAddressPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.companyAddressPopupService
                .open(CompanyAddressDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
