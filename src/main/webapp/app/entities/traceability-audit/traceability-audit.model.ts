import { BaseEntity } from './../../shared';
import {Company} from '../company/company.model';
import {CompanyContactPerson} from '../company-contact-person/company-contact-person.model';
import {AuditProcess} from '../audit-process/audit-process.model';

export class TraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public status?: string,
        public creationDate?: any,
        public auditProcesses?: AuditProcess[],
        public companyId?: number,
        public company?: Company,
        public companyContactPersonId?: number,
        public companyContactPerson?: CompanyContactPerson
    ) {
    }
}
