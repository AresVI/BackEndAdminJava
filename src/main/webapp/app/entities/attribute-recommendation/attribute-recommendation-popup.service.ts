import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AttributeRecommendation } from './attribute-recommendation.model';
import { AttributeRecommendationService } from './attribute-recommendation.service';

@Injectable()
export class AttributeRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private attributeRecommendationService: AttributeRecommendationService

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
                this.attributeRecommendationService.find(id).subscribe((attributeRecommendation) => {
                    this.ngbModalRef = this.attributeRecommendationModalRef(component, attributeRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.attributeRecommendationModalRef(component, new AttributeRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    attributeRecommendationModalRef(component: Component, attributeRecommendation: AttributeRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.attributeRecommendation = attributeRecommendation;
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
