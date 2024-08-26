import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = `${environment.apiUrl}/api/game`;

  constructor(private httpClient: HttpClient) {}

  createNewGame(): Observable<any> {
    return this.httpClient.post(this.baseUrl, null);
  }

  playRound(option: string): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/play/${option}`, null);
  }

  getOptions(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/options`);
  }
}
