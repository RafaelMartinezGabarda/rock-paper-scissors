import { Routes } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from './core/services/auth.service';
import { HomeComponent } from './features/home/home.component';

export const routes: Routes = [
  {
    path: '',
    canActivate: [
      () => {
        return inject(AuthService).loaded.asObservable().subscribe();
      },
    ],
    children: [
      { path: '', component: HomeComponent },
      { path: 'home', redirectTo: '' },
    ],
  },
];
