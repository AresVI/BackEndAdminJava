import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CancelationTraceabilityAudit } from './cancelation-traceability-audit.model';
import { CancelationTraceabilityAuditService } from './cancelation-traceability-audit.service';

@Injectable()
export class CancelationTraceabilityAuditPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private cancelationTraceabilityAuditService: CancelationTraceabilityAuditService

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
                this.cancelationTraceabilityAuditService.find(id).subscribe((cancelationTraceabilityAudit) => {
                    this.ngbModalRef = this.cancelationTraceabilityAuditModalRef(component, cancelationTraceabilityAudit);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.cancelationTraceabilityAuditModalRef(component, new CancelationTraceabilityAudit());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    cancelationTraceabilityAuditModalRef(component: Component, cancelationTraceabilityAudit: CancelationTraceabilityAudit): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.cancelationTraceabilityAudit = cancelationTraceabilityAudit;
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
