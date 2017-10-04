import { BaseEntity } from './../../shared';

export class CategoryAttRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public auditTaskRecomId?: number,
        public categoryAttributeId?: number,
    ) {
    }
}
