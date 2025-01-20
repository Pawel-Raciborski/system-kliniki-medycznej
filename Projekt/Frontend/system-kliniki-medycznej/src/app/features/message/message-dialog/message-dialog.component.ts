import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-message-dialog',
  standalone: true,
  imports: [],
  templateUrl: './message-dialog.component.html',
  styleUrl: './message-dialog.component.css'
})
export class MessageDialogComponent {


  constructor(
    @Inject(MAT_DIALOG_DATA) public content: {message: string, type: "error" | "success" | "warning"},
    private dialogRef: MatDialogRef<MessageDialogComponent>
  ) {
    console.log(content);
  }

  get type(){
    return this.content.type;
  }

  getMessage() {
    if(this.type === 'error'){
      return "Błąd";
    }else if(this.type === 'success'){
      return "Sukces"
    }
    return "Ostrzeżenie";
  }

  close() {
    this.dialogRef.close();
  }
}
