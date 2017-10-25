import { BaseEntity } from './../../shared';

export class AttributeRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public implemented?: boolean,
        public categoryAttRecomId?: number,
        public attributeId?: number,
    ) {
    }
}
