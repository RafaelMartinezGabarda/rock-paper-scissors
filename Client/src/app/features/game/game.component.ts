import { Component, OnInit } from '@angular/core';
import { GameService } from './game.service';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from '../../shared/components/spinner/spinner.component';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [CommonModule, SpinnerComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss',
})
export class GameComponent implements OnInit {
  game: any | undefined;
  options: any | undefined;
  isLoading = false;

  constructor(private gameService: GameService) {}

  async ngOnInit(): Promise<void> {
    this.gameService.getOptions().subscribe((result) => {
      this.options = result;
    });
  }

  playRound(option: string) {
    this.isLoading = true;

    this.gameService.playRound(option).subscribe({
      next: (result) => {
        this.game = result;
      },
      error: () => {
        this.isLoading = false;
      },
      complete: () => {
        this.isLoading = false;
      },
    });
  }

  newGame() {
    this.gameService.createNewGame().subscribe((result) => {
      this.game = result;
    });
  }
}
