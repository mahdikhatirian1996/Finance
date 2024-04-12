// @ts-nocheck
import {interval, Subscription} from 'rxjs';
import {Component, Directive, OnInit, OnDestroy} from '@angular/core';
import {NewPairService} from "../../services/newpair/newpair.service";
import {NewpairWithoutHIModel} from "../../../models/newpair/newpairWithoutHI.model";
import {NewpairWithoutHIService} from "../../../services/newpair/withoutHoneypot/newpairWithoutHI.service";

@Component({
  selector: 'app-newpair-list',
  templateUrl: './newpairWithoutHI.component.html'
})
export class NewpairWithoutHIComponent implements OnInit, OnDestroy {
  // --- Grid Params ---
  newPairModels: NewpairWithoutHIModel[] = [];
  hasInterval: boolean = false;
  subscription: Subscription = new Subscription();
  // --- Page Params ---
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalItems: number = 0;
  totalPages: number = Math.ceil(this.totalItems / this.itemsPerPage);

  constructor(private newPairService: NewpairWithoutHIService) {
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
        this.totalItems = data.count;
        this.totalPages = Math.ceil(data.totalItems / this.itemsPerPage);
        this.setTimestamp();
        this.roundingNumber();
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
        this.newPairModels[i].createdDateDITimestamp =
          new Date(this.newPairModels[i].createdDateDI).toLocaleDateString() + " -- " + new Date(this.newPairModels[i].createdDateDI).toLocaleTimeString();
      }
      if (this.newPairModels || this.newPairModels[i].insertedDate) {
        this.newPairModels[i].insertedDateTimestamp =
          new Date(this.newPairModels[i].insertedDate).toLocaleDateString() + " -- " + new Date(this.newPairModels[i].insertedDate).toLocaleTimeString();
      }
    }
  }

  roundingNumber() {
    for (let i = 0; i < this.newPairModels.length; i++) {
      if (this.newPairModels && this.newPairModels[i].liquidityDI !== null) {
        this.newPairModels[i].liquidityDI =
          this.newPairModels[i].liquidityDI.split(".")[0] + "." + this.newPairModels[i].liquidityDI.split(".")[1].slice(0, 2);
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
