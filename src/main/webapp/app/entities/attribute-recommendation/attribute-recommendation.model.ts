import { BaseEntity } from './../../shared';
import {Attribute} from '../attribute/attribute.model';

export class AttributeRecommendation implements BaseEntity {
    constructor(
        public id?: number,
        public description?: any,
        public implemented?: boolean,
        public categoryAttRecomId?: number,
        public attributeId?: number,
        public attribute?: Attribute,
    ) {
    }
}
