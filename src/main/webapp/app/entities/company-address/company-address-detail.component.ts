import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CompanyAddress } from './company-address.model';
import { CompanyAddressService } from './company-address.service';

@Component({
    selector: 'jhi-company-address-detail',
    templateUrl: './company-address-detail.component.html'
})
export class CompanyAddressDetailComponent implements OnInit, OnDestroy {

    companyAddress: CompanyAddress;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private companyAddressService: CompanyAddressService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCompanyAddresses();
    }

    load(id) {
        this.companyAddressService.find(id).subscribe((companyAddress) => {
            this.companyAddress = companyAddress;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompanyAddresses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'companyAddressListModification',
            (response) => this.load(this.companyAddress.id)
        );
    }
}
