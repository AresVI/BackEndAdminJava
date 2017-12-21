import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AuditAttributeAnalysis } from './audit-attribute-analysis.model';
import { AuditAttributeAnalysisService } from './audit-attribute-analysis.service';

@Injectable()
export class AuditAttributeAnalysisPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private auditAttributeAnalysisService: AuditAttributeAnalysisService

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
                this.auditAttributeAnalysisService.find(id).subscribe((auditAttributeAnalysis) => {
                    this.ngbModalRef = this.auditAttributeAnalysisModalRef(component, auditAttributeAnalysis);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.auditAttributeAnalysisModalRef(component, new AuditAttributeAnalysis());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    auditAttributeAnalysisModalRef(component: Component, auditAttributeAnalysis: AuditAttributeAnalysis): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.auditAttributeAnalysis = auditAttributeAnalysis;
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
