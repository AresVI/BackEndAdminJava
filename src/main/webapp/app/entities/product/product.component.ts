import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Product } from './product.model';
import { ProductService } from './product.service';
import { Principal, ResponseWrapper } from '../../shared';
import {CompanyProduct} from './company_product.model';

@Component({
    selector: 'jhi-product',
    templateUrl: './product.component.html'
})
export class ProductComponent implements OnInit, OnDestroy {

currentAccount: any;
    company_product: CompanyProduct;
    company_id: number;
    error: any;
    success: any;
    eventSubscriber: Subscription;
    subscription: Subscription;
    routeData: any;
    links: any;
    predicate: any;

    constructor(
        private productService: ProductService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private route: ActivatedRoute
    ) {
        this.subscription = this.route.params.subscribe((params) => {
            this.company_id = params['company_id'];
        });
    }

    loadAll() {
        this.productService.findAll(this.company_id).subscribe((company_product) => {
            this.company_product = company_product;
        });
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInProducts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Product) {
        return item.id;
    }
    registerChangeInProducts() {
        this.eventSubscriber = this.eventManager.subscribe('productListModification', (response) => this.loadAll());
    }

    private onSuccess(data) {
        this.company_product = data;
        console.log(this.company_product)
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
