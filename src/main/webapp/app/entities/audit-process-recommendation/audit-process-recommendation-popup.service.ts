import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuditProcessRecommendation } from './audit-process-recommendation.model';
import { AuditProcessRecommendationService } from './audit-process-recommendation.service';

@Injectable()
export class AuditProcessRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private auditProcessRecommendationService: AuditProcessRecommendationService

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
                this.auditProcessRecommendationService.find(id).subscribe((auditProcessRecommendation) => {
                    this.ngbModalRef = this.auditProcessRecommendationModalRef(component, auditProcessRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.auditProcessRecommendationModalRef(component, new AuditProcessRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    auditProcessRecommendationModalRef(component: Component, auditProcessRecommendation: AuditProcessRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.auditProcessRecommendation = auditProcessRecommendation;
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
