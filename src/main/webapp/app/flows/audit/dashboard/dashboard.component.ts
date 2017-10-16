import {Component, OnInit} from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import {ActivatedRoute} from '@angular/router';

import { Principal } from '../../../shared';

@Component({
    selector: 'jhi-flow-audit-dashboard',
    templateUrl: './dashboard.component.html',
})

export class DashBoardComponent implements OnInit {
    currentAccount: Account;
    modalRef: NgbModalRef;

    error: any;
    success: any;
    routeData: any;
    predicate: any;

    constructor(
        private principal: Principal,
        private activatedRoute: ActivatedRoute
    ) {
        this.routeData = this.activatedRoute.data.subscribe((data) => {});
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }
}
