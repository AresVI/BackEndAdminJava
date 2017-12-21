import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuditTaskStandardObservation } from './audit-task-standard-observation.model';
import { AuditTaskStandardObservationService } from './audit-task-standard-observation.service';

@Injectable()
export class AuditTaskStandardObservationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private auditTaskStandardObservationService: AuditTaskStandardObservationService

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
                this.auditTaskStandardObservationService.find(id).subscribe((auditTaskStandardObservation) => {
                    this.ngbModalRef = this.auditTaskStandardObservationModalRef(component, auditTaskStandardObservation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.auditTaskStandardObservationModalRef(component, new AuditTaskStandardObservation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    auditTaskStandardObservationModalRef(component: Component, auditTaskStandardObservation: AuditTaskStandardObservation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.auditTaskStandardObservation = auditTaskStandardObservation;
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
