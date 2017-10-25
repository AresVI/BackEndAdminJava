import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoryAttrRecommendation } from './category-attr-recommendation.model';
import { CategoryAttrRecommendationService } from './category-attr-recommendation.service';

@Injectable()
export class CategoryAttrRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private categoryAttrRecommendationService: CategoryAttrRecommendationService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.categoryAttrRecommendationService.find(id).subscribe((categoryAttrRecommendation) => {
                    this.ngbModalRef = this.categoryAttrRecommendationModalRef(component, categoryAttrRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.categoryAttrRecommendationModalRef(component, new CategoryAttrRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    categoryAttrRecommendationModalRef(component: Component, categoryAttrRecommendation: CategoryAttrRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.categoryAttrRecommendation = categoryAttrRecommendation;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
