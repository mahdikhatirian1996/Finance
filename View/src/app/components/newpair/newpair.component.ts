// @ts-nocheck
import {interval, Subscription} from 'rxjs';
import {Component, Directive, OnInit, OnDestroy} from '@angular/core';
import {NewPairModel} from "../../models/newpair/newpair.model";
import {NewPairService} from "../../services/newpair/newpair.service";

@Component({
  selector: 'app-newpair-list',
  templateUrl: './newpair.component.html'
})
export class NewpairComponent implements OnInit, OnDestroy {
  // --- Grid Params ---
  newPairModels: NewPairModel[] = [];
  hasInterval: boolean = false;
  subscription: Subscription = new Subscription();
  // --- Page Params ---
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalItems: number = 0;
  totalPages: number = Math.ceil(this.totalItems / this.itemsPerPage);

  constructor(private newPairService: NewPairService) {
  }

  ngOnInit(): void {
    this.setInterval();
    this.getAll();
  }

  setInterval() {
    const intervalOperator = interval(5000);
    this.subscription = intervalOperator.subscribe(() => {
      if (this.hasInterval) {
        this.refreshList();
      }
    })
  }

  getAll(): void {
    this.newPairService.getPaginatedData(this.currentPage - 1, this.itemsPerPage).subscribe({
      next: (data) => {
        this.newPairModels = data.list;
        this.setTimestamp();
        this.roundingNumber();
        this.totalItems = data.count;
        this.totalPages = Math.ceil(data.totalItems / this.itemsPerPage);
      },
      error: (e) => console.error(e)
    });
  }

  onPageChange(page: number): void {
    this.currentPage = page;
    this.getAll();
  }

  refreshList(): void {
    this.currentPage = 1;
    this.getAll();
  }

  setTimestamp(): void {
    for (let i = 0; i < this.newPairModels.length; i++) {
      if (this.newPairModels || this.newPairModels[i].createdDateDI !== null) {
        this.newPairModels[i].createdDateDITimestamp = new Date(this.newPairModels[i].createdDateDI).toISOString();
      }
      if (this.newPairModels || this.newPairModels[i].createdDateHI !== null) {
        this.newPairModels[i].createdDateHITimestamp = new Date(this.newPairModels[i].createdDateHI).toISOString();
      }
    }
  }

  roundingNumber() {
    for (let i = 0; i < this.newPairModels.length; i++) {
      if (this.newPairModels) {
        this.newPairModels[i].liquidityHI =
          this.newPairModels[i].liquidityHI.split(".")[0] + "." + this.newPairModels[i].liquidityHI.split(".")[1].slice(0, 2);
        this.newPairModels[i].liquidityDI =
          this.newPairModels[i].liquidityDI.split(".")[0] + "." + this.newPairModels[i].liquidityDI.split(".")[1].slice(0, 2);
        this.newPairModels[i].averageGas = this.newPairModels[i].averageGas.split(".")[0];
        this.newPairModels[i].buyGas = this.newPairModels[i].buyGas.split(".")[0];
        this.newPairModels[i].sellGas = this.newPairModels[i].sellGas.split(".")[0];
        this.newPairModels[i].averageTax = this.newPairModels[i].averageTax.split(".")[0];
        this.newPairModels[i].buyTax = this.newPairModels[i].buyTax.split(".")[0];
        this.newPairModels[i].sellTax = this.newPairModels[i].sellTax.split(".")[0];
        this.newPairModels[i].transferTax = this.newPairModels[i].transferTax.split(".")[0];
      }
    }
  }

  changeHasInterval() {
    if (this.hasInterval) {
      this.hasInterval = false;
    } else if (!this.hasInterval) {
      this.hasInterval = true;
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
