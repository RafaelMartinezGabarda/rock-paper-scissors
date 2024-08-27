import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = `${environment.apiUrl}/api/game`;

  constructor(private httpClient: HttpClient) {}

  createNewGame(): Observable<any> {
    return this.httpClient.post(this.baseUrl, null).pipe(
      catchError((error) => {
        // Handle the error, I keep it empty for the showcase
        throw error;
      })
    );
  }

  playRound(option: string): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/play/${option}`, null).pipe(
      catchError((error) => {
        // Handle the error, I keep it empty for the showcase
        throw error;
      })
    );
  }

  getOptions(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/options`).pipe(
      catchError((error) => {
        alert('Could not get options');
        throw error;
      })
    );
  }
}
