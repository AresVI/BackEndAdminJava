import { BaseEntity } from './../../shared';
import {AuditTask} from '../audit-task/audit-task.model';
import {CategoryAttrRecommendation} from '../category-attr-recommendation/category-attr-recommendation.model';

export class AuditTaskRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public auditProcessRecomId?: number,
        public auditTaskId?: number,
        public reviewed?: boolean,
        public auditTask?: AuditTask,
        public categoryAttrRecommendationSet?: CategoryAttrRecommendation[],
    ) {
    }
}
