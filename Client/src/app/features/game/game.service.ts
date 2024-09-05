import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { GameResult } from './models/game';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = `${environment.apiUrl}/api/game`;

  constructor(private httpClient: HttpClient) {}

  createNewGame(): Observable<GameResult> {
    return this.httpClient.post<GameResult>(this.baseUrl, null).pipe(
      catchError((error) => {
        // Handle the error, I keep it empty for the showcase
        throw error;
      })
    );
  }

  playRound(id: number, option: string): Observable<GameResult> {
    return this.httpClient
      .put<GameResult>(`${this.baseUrl}/play/${id}`, option)
      .pipe(
        catchError((error) => {
          alert('Could not make move');
          throw error;
        })
      );
  }

  getOptions(): Observable<string[]> {
    return this.httpClient.get<string[]>(`${this.baseUrl}/options`).pipe(
      catchError((error) => {
        alert('Could not get options');
        throw error;
      })
    );
  }
}
