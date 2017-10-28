import { BaseEntity } from './../../shared';
import {CategoryAttribute} from '../category-attribute/category-attribute.model';
import {AttributeRecommendation} from '../attribute-recommendation/attribute-recommendation.model';

export class CategoryAttrRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public auditTaskRecomId?: number,
        public categoryAttributeId?: number,
        public categoryAttribute?: CategoryAttribute,
        public attributeRecommendationSet?: AttributeRecommendation[],
    ) {
    }
}
