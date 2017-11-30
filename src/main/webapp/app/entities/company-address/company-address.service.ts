import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { CompanyAddress } from './company-address.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CompanyAddressService {

    private resourceUrl = SERVER_API_URL + 'api/company-addresses';

    constructor(private http: Http) { }

    create(companyAddress: CompanyAddress): Observable<CompanyAddress> {
        const copy = this.convert(companyAddress);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(companyAddress: CompanyAddress): Observable<CompanyAddress> {
        const copy = this.convert(companyAddress);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<CompanyAddress> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(companyAddress: CompanyAddress): CompanyAddress {
        const copy: CompanyAddress = Object.assign({}, companyAddress);
        return copy;
    }
}
