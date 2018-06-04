import { BaseEntity } from './../../shared';
import {AuditTaskRecommendation} from '../audit-task-recommendation';

export class TraceAudit implements BaseEntity {
    constructor(
        public id?: number,
        public traceabilityAuditId?: number,
        public auditTaskRecommendationId?: number,
        public auditTaskRecommendation?: AuditTaskRecommendation,
    ) {
    }
}
