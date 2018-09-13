import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { Attribute } from './attribute.model';
import { AttributeService } from './attribute.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-attribute',
    templateUrl: './attribute.component.html'
})
export class AttributeComponent implements OnInit, OnDestroy {

    @Input() category_attribute_id: number;

    attributes: Attribute[];
    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;

    constructor(
        private attributeService: AttributeService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private principal: Principal
    ) {
        this.attributes = [];
    }

    loadAll() {
        this.attributeService.findAttributeByCategoryAttributeId(this.category_attribute_id).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    reset() {
        this.page = 0;
        this.attributes = [];
        this.loadAll();
    }

    loadPage(page) {
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.registerChangeInAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Attribute) {
        return item.id;
    }

    registerChangeInAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('attributeListModification', (response) => this.reset());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.attributes = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
