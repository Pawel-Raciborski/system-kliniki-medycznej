import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-table-options',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './table-options.component.html',
  styleUrl: './table-options.component.css'
})
export class TableOptionsComponent implements OnInit {
  @Input() foundElements = 0;
  @Input({required: true}) paginationOptions!: { page: number; pageSize: number };
  pageSizeControl!: FormControl;
  @Output() pageSizeEmitter = new EventEmitter<number>();
  availablePageSizes = [
    5,
    10,
    20
  ];
  ngOnInit(): void {
    this.pageSizeControl = new FormControl<number>(+this.paginationOptions.pageSize);
  }

  updateValue() {
    this.pageSizeEmitter.emit(this.pageSizeControl.value);
  }
}

