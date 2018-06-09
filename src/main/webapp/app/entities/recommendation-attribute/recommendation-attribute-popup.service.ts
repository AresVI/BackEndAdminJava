import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { RecommendationAttribute } from './recommendation-attribute.model';
import { RecommendationAttributeService } from './recommendation-attribute.service';

@Injectable()
export class RecommendationAttributePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private recommendationAttributeService: RecommendationAttributeService

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
                this.recommendationAttributeService.find(id).subscribe((recommendationAttribute) => {
                    this.ngbModalRef = this.recommendationAttributeModalRef(component, recommendationAttribute);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.recommendationAttributeModalRef(component, new RecommendationAttribute());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    recommendationAttributeModalRef(component: Component, recommendationAttribute: RecommendationAttribute): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.recommendationAttribute = recommendationAttribute;
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
