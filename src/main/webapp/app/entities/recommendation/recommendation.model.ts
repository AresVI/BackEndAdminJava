import { BaseEntity } from './../../shared';
import {AuditProcessRecommendation} from '../audit-process-recommendation/audit-process-recommendation.model';
import {Auditor} from '../auditor/auditor.model';

export class Recommendation implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public qualification?: number,
        public levelComputerization?: number,
        public creationDate?: any,
        public reviewed?: boolean,
        public auditorId?: number,
        public auditor?: Auditor,
        public traceabilityAuditId?: number,
        public auditProcessRecommendationSet?: AuditProcessRecommendation[]
    ) {
    }
}
