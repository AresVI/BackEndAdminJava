import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Glossary } from './glossary.model';
import { GlossaryService } from './glossary.service';

@Component({
    selector: 'jhi-glossary-detail',
    templateUrl: './glossary-detail.component.html'
})
export class GlossaryDetailComponent implements OnInit, OnDestroy {

    glossary: Glossary;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private glossaryService: GlossaryService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGlossaries();
    }

    load(id) {
        this.glossaryService.find(id).subscribe((glossary) => {
            this.glossary = glossary;
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

    registerChangeInGlossaries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'glossaryListModification',
            (response) => this.load(this.glossary.id)
        );
    }
}
