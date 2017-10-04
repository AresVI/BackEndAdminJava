import { BaseEntity } from './../../shared';

export class AuditProcessRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public recommendationId?: number,
        public auditProcessId?: number,
    ) {
    }
}
