import { BaseEntity } from './../../shared';

export class AuditProcess implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public traceabilityAuditId?: number,
    ) {
    }
}
