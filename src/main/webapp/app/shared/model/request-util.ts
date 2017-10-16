import { URLSearchParams, BaseRequestOptions } from '@angular/http';

export const createRequestOption = (req?: any): BaseRequestOptions => {
    const options: BaseRequestOptions = new BaseRequestOptions();
    if (req) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('pagination', 'true');
        params.set('page', req.page);
        params.set('size', req.size);
        if (req.sort) {
            params.paramsMap.set('sort', req.sort);
        }
        params.set('query', req.query);

        options.params = params;
    }
    return options;
};

export const createRequestOptionWithQuery = (req?: any, query_object?: object): BaseRequestOptions => {
    const options: BaseRequestOptions = new BaseRequestOptions();
    if (req) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('pagination', 'true');
        params.set('page', req.page);
        params.set('size', req.size);

        if (query_object) {

            Object.keys(query_object).map((key) => {
                params.set(key, query_object[key]);
            });

        }

        if (req.sort) {
            params.paramsMap.set('sort', req.sort);
        }
        params.set('query', req.query);

        options.params = params;
    }
    return options;
};

export const createRequestOptionAllElements = (req?: any): BaseRequestOptions => {
    const options: BaseRequestOptions = new BaseRequestOptions();
    if (req) {
        const params: URLSearchParams = new URLSearchParams();
        params.set('pagination', 'false');
        params.set('query', req.query);

        options.params = params;
    }
    return options;
};
