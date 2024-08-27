import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { GameComponent } from './features/game/game.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, GameComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'Client';
}
