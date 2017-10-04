import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Container } from './container.model';
import { ContainerService } from './container.service';

@Component({
    selector: 'jhi-container-detail',
    templateUrl: './container-detail.component.html'
})
export class ContainerDetailComponent implements OnInit, OnDestroy {

    container: Container;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private containerService: ContainerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInContainers();
    }

    load(id) {
        this.containerService.find(id).subscribe((container) => {
            this.container = container;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInContainers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'containerListModification',
            (response) => this.load(this.container.id)
        );
    }
}
