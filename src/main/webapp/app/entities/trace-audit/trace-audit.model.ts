import { BaseEntity } from './../../shared';

export class TraceAudit implements BaseEntity {
    constructor(
        public id?: number,
        public traceabilityAuditId?: number,
        public auditTaskRecommendationId?: number,
    ) {
    }
}
