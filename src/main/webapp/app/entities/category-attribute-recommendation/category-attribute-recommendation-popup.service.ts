import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoryAttributeRecommendation } from './category-attribute-recommendation.model';
import { CategoryAttributeRecommendationService } from './category-attribute-recommendation.service';

@Injectable()
export class CategoryAttributeRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private categoryAttributeRecommendationService: CategoryAttributeRecommendationService

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
                this.categoryAttributeRecommendationService.find(id).subscribe((categoryAttributeRecommendation) => {
                    this.ngbModalRef = this.categoryAttributeRecommendationModalRef(component, categoryAttributeRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.categoryAttributeRecommendationModalRef(component, new CategoryAttributeRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    categoryAttributeRecommendationModalRef(component: Component,
        categoryAttributeRecommendation: CategoryAttributeRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.categoryAttributeRecommendation = categoryAttributeRecommendation;
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
