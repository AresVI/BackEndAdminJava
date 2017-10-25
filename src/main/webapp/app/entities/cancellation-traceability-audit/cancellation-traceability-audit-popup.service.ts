import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CancellationTraceabilityAudit } from './cancellation-traceability-audit.model';
import { CancellationTraceabilityAuditService } from './cancellation-traceability-audit.service';

@Injectable()
export class CancellationTraceabilityAuditPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private cancellationTraceabilityAuditService: CancellationTraceabilityAuditService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, traceability_audit_id?: number, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.cancellationTraceabilityAuditService.find(id).subscribe((cancellationTraceabilityAudit) => {
                    this.ngbModalRef = this.cancellationTraceabilityAuditModalRef(component, cancellationTraceabilityAudit);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError

                const cancellationTraceabilityAudit = new CancellationTraceabilityAudit();

                cancellationTraceabilityAudit.traceabilityAuditId = traceability_audit_id;

                setTimeout(() => {
                    this.ngbModalRef = this.cancellationTraceabilityAuditModalRef(component, cancellationTraceabilityAudit);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    cancellationTraceabilityAuditModalRef(component: Component, cancellationTraceabilityAudit: CancellationTraceabilityAudit): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.cancellationTraceabilityAudit = cancellationTraceabilityAudit;
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
