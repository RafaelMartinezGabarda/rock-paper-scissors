import { Component, EventEmitter, Input, Output } from '@angular/core';
import { GameResult } from '../models/game';
import { SpinnerComponent } from '../../../shared/components/spinner/spinner.component';

@Component({
  selector: 'app-playground',
  standalone: true,
  imports: [SpinnerComponent],
  templateUrl: './playground.component.html',
  styleUrl: './playground.component.scss',
})
export class PlaygroundComponent {
  @Input({ required: true }) game: GameResult | undefined;
  @Input({ required: true }) options: string[] | undefined;
  @Input() isLoading = true;

  @Output() optionSelected = new EventEmitter<string>();

  public lastOption: string | undefined;

  public onOptionSelected(option: string) {
    this.lastOption = option;
    this.optionSelected.emit(option);
  }
}
