import { BaseEntity } from './../../shared';

export class CompanyAddress implements BaseEntity {
    constructor(
        public id?: number,
        public street?: string,
        public city?: string,
        public state?: string,
        public latitude?: string,
        public longitude?: string,
        public companyId?: number,
    ) {
    }
}
