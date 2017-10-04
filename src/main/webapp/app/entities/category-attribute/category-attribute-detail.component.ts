import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryAttribute } from './category-attribute.model';
import { CategoryAttributeService } from './category-attribute.service';

@Component({
    selector: 'jhi-category-attribute-detail',
    templateUrl: './category-attribute-detail.component.html'
})
export class CategoryAttributeDetailComponent implements OnInit, OnDestroy {

    categoryAttribute: CategoryAttribute;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private categoryAttributeService: CategoryAttributeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategoryAttributes();
    }

    load(id) {
        this.categoryAttributeService.find(id).subscribe((categoryAttribute) => {
            this.categoryAttribute = categoryAttribute;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCategoryAttributes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryAttributeListModification',
            (response) => this.load(this.categoryAttribute.id)
        );
    }
}
