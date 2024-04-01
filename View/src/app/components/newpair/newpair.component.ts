import {Component, OnInit} from '@angular/core';
import {Tutorial} from '../../models/tutorial.model';
import {NewPairModel} from "../../models/newpair/newpair.model";
import {NewPairService} from "../../services/newpair/newpair.service";

@Component({
  selector: 'app-newpair-list',
  templateUrl: './newpair.component.html'
})
export class NewpairComponent implements OnInit {
  newPairModels?: NewPairModel[];
  currentTutorial: Tutorial = {};
  currentIndex = -1;
  title = '';

  constructor(private newPairService: NewPairService) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.newPairService.getAll().subscribe({
      next: (data) => {
        this.newPairModels = data;
        console.log(data);
      },
      error: (e) => console.error(e)
    });
  }

  refreshList(): void {
    this.getAll();
    this.currentTutorial = {};
    this.currentIndex = -1;
  }

}
