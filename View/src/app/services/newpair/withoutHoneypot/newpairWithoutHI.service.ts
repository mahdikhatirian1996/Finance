import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {NewPairModel} from "../../../models/newpair/newpair.model";

const baseUrl = 'http://localhost:8080/api/newpair';

@Injectable({
  providedIn: 'root',
})
export class NewpairWithoutHIService {
  constructor(private http: HttpClient) {}

  getPaginatedData(page: number, itemsPerPage: number): Observable<any> {
    const params = new HttpParams()
      .set('currentPage', page.toString())
      .set('pageSize', itemsPerPage.toString());
    return this.http.get<NewPairModel[]>(baseUrl + '/getAll/withoutHoneypot' + '?currentPage=' + page + '&pageSize=' + itemsPerPage);
  }
}
