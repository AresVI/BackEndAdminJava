import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuditProcessStandardObservation } from './audit-process-standard-observation.model';
import { AuditProcessStandardObservationService } from './audit-process-standard-observation.service';

@Injectable()
export class AuditProcessStandardObservationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private auditProcessStandardObservationService: AuditProcessStandardObservationService

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
                this.auditProcessStandardObservationService.find(id).subscribe((auditProcessStandardObservation) => {
                    this.ngbModalRef = this.auditProcessStandardObservationModalRef(component, auditProcessStandardObservation);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.auditProcessStandardObservationModalRef(component, new AuditProcessStandardObservation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    auditProcessStandardObservationModalRef(component: Component,
        auditProcessStandardObservation: AuditProcessStandardObservation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.auditProcessStandardObservation = auditProcessStandardObservation;
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
