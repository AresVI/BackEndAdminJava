import { BaseEntity } from './../../shared';

export class TraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public creationDate?: any,
        public auditProcesses?: BaseEntity[],
        public companyId?: number,
        public productTypeId?: number,
    ) {
    }
}
