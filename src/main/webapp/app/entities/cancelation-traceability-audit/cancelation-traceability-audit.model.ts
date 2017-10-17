import { BaseEntity } from './../../shared';

export class CancelationTraceabilityAudit implements BaseEntity {
    constructor(
        public id?: number,
        public justification?: string,
        public traceabilityAuditId?: number,
        public userId?: number,
    ) {
    }
}