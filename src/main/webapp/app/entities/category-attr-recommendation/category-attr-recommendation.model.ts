import { BaseEntity } from './../../shared';

export class CategoryAttrRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public auditTaskRecomId?: number,
        public categoryAttributeId?: number,
    ) {
    }
}
