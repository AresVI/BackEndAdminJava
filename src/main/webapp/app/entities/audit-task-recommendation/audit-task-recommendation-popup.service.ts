import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuditTaskRecommendation } from './audit-task-recommendation.model';
import { AuditTaskRecommendationService } from './audit-task-recommendation.service';

@Injectable()
export class AuditTaskRecommendationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private auditTaskRecommendationService: AuditTaskRecommendationService

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
                this.auditTaskRecommendationService.find(id).subscribe((auditTaskRecommendation) => {
                    this.ngbModalRef = this.auditTaskRecommendationModalRef(component, auditTaskRecommendation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.auditTaskRecommendationModalRef(component, new AuditTaskRecommendation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    auditTaskRecommendationModalRef(component: Component, auditTaskRecommendation: AuditTaskRecommendation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.auditTaskRecommendation = auditTaskRecommendation;
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
