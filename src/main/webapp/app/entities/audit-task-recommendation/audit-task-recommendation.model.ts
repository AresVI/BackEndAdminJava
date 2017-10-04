import { BaseEntity } from './../../shared';

export class AuditTaskRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public auditProcessRecomId?: number,
        public auditTaskId?: number,
    ) {
    }
}
