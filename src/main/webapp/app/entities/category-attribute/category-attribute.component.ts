import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { CategoryAttribute } from './category-attribute.model';
import { CategoryAttributeService } from './category-attribute.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-category-attributes',
    templateUrl: './category-attribute.component.html'
})

export class CategoryAttributeComponent implements OnInit, OnDestroy {

    @Input() audit_task_id: number;

    currentAccount: any;
    categoryAttributes: CategoryAttribute[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private categoryAttributeService: CategoryAttributeService,
        private parseLinks: JhiParseLinks,
        private alertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.categoryAttributes = [];
    }

    loadAll() {
        this.categoryAttributeService.findCategoryAttributesByAuditTaskId(this.audit_task_id).subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    clear() {
        this.page = 0;
        this.router.navigate(['/category-attribute', {
            page: this.page,
            sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        }]);
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCategoryAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CategoryAttribute) {
        return item.id;
    }
    registerChangeInCategoryAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('categoryAttributeListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onSuccess(data, headers) {
        this.categoryAttributes = data;
    }
    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
