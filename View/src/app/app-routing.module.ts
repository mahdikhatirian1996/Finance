import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NewpairComponent} from "./components/newpair/newpair.component";

const routes: Routes = [
  { path: '', redirectTo: 'newpairInfo', pathMatch: 'full' },
  { path: 'newpairInfo', component: NewpairComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
