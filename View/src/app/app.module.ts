import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NewpairComponent} from "./components/newpair/newpair.component";
import {PaginationComponent} from "./components/base/pagination/pagination.component";
import {MatInputModule} from "@angular/material/input";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSortModule} from "@angular/material/sort";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {CdkCopyToClipboard} from "@angular/cdk/clipboard";
import {CopyTextDirective} from "./components/base/copyDirectiveToClipboard/copyDirective.component";
import {NewpairWithoutHIComponent} from "./components/newpair/withoutHoneypot/newpairWithoutHI.component";

@NgModule({
  declarations: [
    PaginationComponent,
    AppComponent,
    NewpairComponent,
    NewpairWithoutHIComponent,
    CopyTextDirective
  ],
    imports: [
        MatInputModule,
        MatPaginatorModule,
        MatProgressSpinnerModule,
        MatSortModule,
        MatTableModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
