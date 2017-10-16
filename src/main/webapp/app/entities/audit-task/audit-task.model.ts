import { BaseEntity } from './../../shared';
import {Container} from '../container/container.model';

export class AuditTask implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public containerId?: number,
        public container?: Container
    ) {
    }
}
