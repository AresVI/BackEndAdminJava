import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Faqs } from './faqs.model';
import { FaqsService } from './faqs.service';

@Component({
    selector: 'jhi-faqs-detail',
    templateUrl: './faqs-detail.component.html'
})
export class FaqsDetailComponent implements OnInit, OnDestroy {

    faqs: Faqs;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private faqsService: FaqsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFaqs();
    }

    load(id) {
        this.faqsService.find(id).subscribe((faqs) => {
            this.faqs = faqs;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFaqs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'faqsListModification',
            (response) => this.load(this.faqs.id)
        );
    }
}
