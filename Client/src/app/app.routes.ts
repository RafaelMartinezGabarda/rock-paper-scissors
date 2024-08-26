import { Routes } from '@angular/router';
import { GameComponent } from './features/game/game.component';

export const routes: Routes = [
  { path: 'game', loadComponent: () => GameComponent },
];
