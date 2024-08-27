import { Component, inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { GameComponent } from '../game/game.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [GameComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  authService = inject(AuthService);
  user = this.authService.user.asObservable();
}
