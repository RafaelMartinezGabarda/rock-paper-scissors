import { Component, OnInit } from '@angular/core';
import { GameService } from './game.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './game.component.html',
  styleUrl: './game.component.scss',
})
export class GameComponent implements OnInit {
  game: any | undefined;
  options: any | undefined;

  constructor(private gameService: GameService) {}

  async ngOnInit(): Promise<void> {
    this.gameService.createNewGame().subscribe((result) => {
      this.game = result;
    });

    this.gameService.getOptions().subscribe((result) => {
      this.options = result;
    });
  }

  playRound(option: string) {
    this.gameService.playRound(option).subscribe((result) => {
      this.game = result;
    });
  }
}
