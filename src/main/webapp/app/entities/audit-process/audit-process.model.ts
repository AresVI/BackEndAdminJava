import { BaseEntity } from './../../shared';
import {Container} from '../container/container.model';

export class AuditProcess implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public containerSet?: Container[]
    ) {
    }
}
