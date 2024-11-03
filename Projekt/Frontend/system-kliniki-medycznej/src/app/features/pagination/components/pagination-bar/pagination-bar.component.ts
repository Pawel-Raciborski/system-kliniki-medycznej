import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-pagination-bar',
  standalone: true,
  imports: [],
  templateUrl: './pagination-bar.component.html',
  styleUrl: './pagination-bar.component.css'
})
export class PaginationBarComponent {
  @Input({required:true}) page!: number;
  @Input() disableNext: boolean = false;
  @Output() pageValueEmitter = new EventEmitter<number>();

  decreaseValue() {
    this.page--;
    if(this.page < 0){
      this.page = 0;
    }
    this.pageValueEmitter.emit(+this.page);
  }

  increaseValue() {
    this.page++;
    this.pageValueEmitter.emit(+this.page);
  }
}
