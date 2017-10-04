import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { TraceabilityAudit } from './traceability-audit.model';
import { TraceabilityAuditService } from './traceability-audit.service';

@Injectable()
export class TraceabilityAuditPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private traceabilityAuditService: TraceabilityAuditService

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
                this.traceabilityAuditService.find(id).subscribe((traceabilityAudit) => {
                    traceabilityAudit.creationDate = this.datePipe
                        .transform(traceabilityAudit.creationDate, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.traceabilityAuditModalRef(component, traceabilityAudit);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.traceabilityAuditModalRef(component, new TraceabilityAudit());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    traceabilityAuditModalRef(component: Component, traceabilityAudit: TraceabilityAudit): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.traceabilityAudit = traceabilityAudit;
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
