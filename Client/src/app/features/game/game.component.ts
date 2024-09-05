import { Component, OnInit } from '@angular/core';
import { GameService } from './game.service';
import { SpinnerComponent } from '../../shared/components/spinner/spinner.component';
import { GameResult } from './models/game';
import { PlaygroundComponent } from './playground/playground.component';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [SpinnerComponent, PlaygroundComponent],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss',
})
export class GameComponent implements OnInit {
  game: GameResult | undefined;
  options: string[] | undefined;
  isLoading = false;

  constructor(private gameService: GameService) {}

  ngOnInit(): void {
    this.gameService.getOptions().subscribe((result) => {
      this.options = result;
    });
  }

  playRound(option: string) {
    this.isLoading = true;

    if (this.game) {
      this.gameService
        .playRound(this.game.id, option)
        .pipe(finalize(() => (this.isLoading = false)))
        .subscribe((result) => (this.game = result));
    } else {
      this.newGame();
    }
  }

  newGame() {
    this.gameService.createNewGame().subscribe((result) => {
      this.game = result;
    });
  }
}
