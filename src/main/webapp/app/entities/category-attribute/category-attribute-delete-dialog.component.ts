import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CategoryAttribute } from './category-attribute.model';
import { CategoryAttributePopupService } from './category-attribute-popup.service';
import { CategoryAttributeService } from './category-attribute.service';

@Component({
    selector: 'jhi-category-attribute-delete-dialog',
    templateUrl: './category-attribute-delete-dialog.component.html'
})
export class CategoryAttributeDeleteDialogComponent {

    categoryAttribute: CategoryAttribute;

    constructor(
        private categoryAttributeService: CategoryAttributeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categoryAttributeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'categoryAttributeListModification',
                content: 'Deleted an categoryAttribute'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-category-attribute-delete-popup',
    template: ''
})
export class CategoryAttributeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryAttributePopupService: CategoryAttributePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.categoryAttributePopupService
                .open(CategoryAttributeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
