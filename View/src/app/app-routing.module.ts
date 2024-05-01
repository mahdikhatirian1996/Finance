import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {NewpairComponent} from "./components/newpair/newpair.component";
import {NewpairWithoutHIComponent} from "./components/newpair/withoutHoneypot/newpairWithoutHI.component";

const routes: Routes = [
  { path: '', redirectTo: 'newpairInfoWithoutHI', pathMatch: 'full' },
  { path: 'newpairInfo', component: NewpairComponent },
  { path: 'newpairInfoWithoutHI', component: NewpairWithoutHIComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
