import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import {CompanyContactPersonService} from '../../../entities/company-contact-person/company-contact-person.service';
import {CompanyService} from '../../../entities/company/company.service';
import {CompanyContactPerson} from '../../../entities/company-contact-person/company-contact-person.model';

@Injectable()
export class CompanyContactPersonAuditStartPopupService {
    private ngbModalRef: NgbModalRef;
    company_id: number;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private company_contact_personService: CompanyContactPersonService,
        private companyService: CompanyService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, company_id: number, id?: number): Promise<NgbModalRef> {

        this.company_id = company_id;

        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }
            this.companyService.find(this.company_id).subscribe((company) => {
                if (id) {
                    this.company_contact_personService.find(company.id, id).subscribe((company_contact_person) => {
                        this.ngbModalRef = this.company_contact_personModalRef(component, company_contact_person);
                        resolve(this.ngbModalRef);
                    });
                } else {
                    // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                    setTimeout(() => {

                        const companyContactPerson: CompanyContactPerson = new CompanyContactPerson();

                        companyContactPerson.company = company;

                        this.ngbModalRef = this.company_contact_personModalRef(component, companyContactPerson);
                        resolve(this.ngbModalRef);
                    }, 0);
                }
            });

        });
    }

    company_contact_personModalRef(component: Component, company_contact_person: CompanyContactPerson): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});

        modalRef.componentInstance.company_contact_person = company_contact_person;
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
