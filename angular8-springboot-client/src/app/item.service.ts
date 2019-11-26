import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

	private baseUrl: string;

  constructor(private http: HttpClient) { 
	this.baseUrl = 'http://localhost:8080/api/items';
  }
  
getItem(itemNumber: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${itemNumber}`);
  }

  createItem(item: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, item);
  }

  updateItem(itemNumber: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${itemNumber}`, value);
  }

  deleteItem(itemNumber: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${itemNumber}`, { responseType: 'text' });
  }

  getItemsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
