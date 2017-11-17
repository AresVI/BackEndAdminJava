import { BaseEntity } from './../../shared';
import {Company} from '../company/company.model';

export class CompanyContactPerson implements BaseEntity {
    constructor(
        public id?: number,
        public first_name?: string,
        public last_name?: string,
        public email?: string,
        public telephone?: string,
        public job_possition?: string,
        public company?: Company,
        public companyId?: number
    ) {
    }
}
