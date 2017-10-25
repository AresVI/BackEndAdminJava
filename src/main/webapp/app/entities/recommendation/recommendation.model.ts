import { BaseEntity } from './../../shared';
import {AuditProcessRecommendation} from '../audit-process-recommendation/audit-process-recommendation.model';

export class Recommendation implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public qualification?: number,
        public creationDate?: any,
        public traceabilityAuditId?: number,
        public auditProcessRecommendationSet?: AuditProcessRecommendation[]
    ) {
    }
}
