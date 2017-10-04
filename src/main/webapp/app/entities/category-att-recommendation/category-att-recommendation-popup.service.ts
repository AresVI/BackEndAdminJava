import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CategoryAttRecommendation } from './category-att-recommendation.model';
import { CategoryAttRecommendationService } from './category-att-recommendation.service';

@Injectable()
export class CategoryAttRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private categoryAttRecommendationService: CategoryAttRecommendationService

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
                this.categoryAttRecommendationService.find(id).subscribe((categoryAttRecommendation) => {
                    this.ngbModalRef = this.categoryAttRecommendationModalRef(component, categoryAttRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.categoryAttRecommendationModalRef(component, new CategoryAttRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    categoryAttRecommendationModalRef(component: Component, categoryAttRecommendation: CategoryAttRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.categoryAttRecommendation = categoryAttRecommendation;
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
