import { BaseEntity } from './../../shared';
import {AuditProcess} from '../audit-process/audit-process.model';
import {AuditTaskRecommendation} from '../audit-task-recommendation/audit-task-recommendation.model';

export class AuditProcessRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public reviewed?: boolean,
        public recommendationId?: number,
        public auditProcessId?: number,
        public auditProcess?: AuditProcess,
        public auditTaskRecommendationSet?: AuditTaskRecommendation[]
    ) {
    }
}
