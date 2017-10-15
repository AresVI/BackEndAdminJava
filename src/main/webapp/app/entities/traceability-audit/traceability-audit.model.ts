import { BaseEntity } from './../../shared';
import {Company} from '../company/company.model';
import {CompanyContactPerson} from '../company-contact-person/company-contact-person.model';

export class TraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public creationDate?: any,
        public auditProcesses?: BaseEntity[],
        public companyId?: number,
        public company?: Company,
        public companyContactPersonId?: number,
        public companyContactPerson?: CompanyContactPerson
    ) {
    }
}
