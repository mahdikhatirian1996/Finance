import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {NewPairModel} from "../../models/newpair/newpair.model";

const baseUrl = 'http://localhost:8080/api/newpair';

@Injectable({
  providedIn: 'root',
})
export class NewPairService {
  constructor(private http: HttpClient) {}

  getPaginatedData(page: number, itemsPerPage: number): Observable<any> {
    const params = new HttpParams()
      .set('currentPage', page.toString())
      .set('pageSize', itemsPerPage.toString());
    return this.http.get<NewPairModel[]>(baseUrl + '/getAll' + '?currentPage=' + page + '&pageSize=' + itemsPerPage);
  }

  getAll(): Observable<NewPairModel[]> {
    return this.http.get<NewPairModel[]>(baseUrl);
  }

  get(contractAddress: string): Observable<NewPairModel> {
    return this.http.get<NewPairModel>(`${baseUrl}/${contractAddress}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(contractAddress: string, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${contractAddress}`, data);
  }

  delete(contractAddress: string): Observable<any> {
    return this.http.delete(`${baseUrl}/${contractAddress}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByTitle(name: string): Observable<NewPairModel[]> {
    return this.http.get<NewPairModel[]>(`${baseUrl}?name=${name}`);
  }
}
