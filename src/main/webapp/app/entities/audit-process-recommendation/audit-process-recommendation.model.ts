import { BaseEntity } from './../../shared';
import {AuditProcess} from '../audit-process/audit-process.model';
import {AuditTaskRecommendation} from '../audit-task-recommendation/audit-task-recommendation.model';
import {Recommendation} from '../recommendation/recommendation.model';

export class AuditProcessRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public reviewed?: boolean,
        public recommendationId?: number,
        public recommendation?: Recommendation,
        public auditProcessId?: number,
        public bonitaBpmCaseId?: number,
        public auditProcess?: AuditProcess,
        public auditTaskRecommendationSet?: AuditTaskRecommendation[]
    ) {
    }
}
