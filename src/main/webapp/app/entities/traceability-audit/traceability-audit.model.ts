import { BaseEntity } from './../../shared';
import {Company} from "../company/company.model";

export class TraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public creationDate?: any,
        public auditProcesses?: BaseEntity[],
        public companyId?: number,
        public company?: Company
    ) {
    }
}
