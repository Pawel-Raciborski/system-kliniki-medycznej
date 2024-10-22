import {Component, OnInit} from '@angular/core';
import {ReceptionistInfo} from '../../../../receptionist/model/receptionist-info';
import {ReceptionistService} from '../../../../receptionist/services/receptionist.service';
import {MatDialog} from '@angular/material/dialog';
import {
  AddReceptionistDialogComponent
} from '../../../../receptionist/dialogs/add-receptionist-dialog/add-receptionist-dialog.component';
import {RegisterReceptionistForm} from '../../../../receptionist/model/register-receptionist-form';

@Component({
  selector: 'app-receptionist-list',
  standalone: true,
  imports: [],
  templateUrl: './receptionist-list.component.html',
  styleUrl: './receptionist-list.component.css'
})
export class ReceptionistListComponent implements OnInit {
  private receptionists!: ReceptionistInfo[]

  constructor(
    private receptionistService: ReceptionistService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.receptionistService.receptionists.subscribe(receptionists => {
      this.receptionists = receptionists;
    });
  }


  openAddReceptionistDialog() {
    this.dialog.open(AddReceptionistDialogComponent, {
      width: '700px',
    }).afterClosed().subscribe((receptionistToRegister: RegisterReceptionistForm) => {
      console.log(receptionistToRegister);
      this.receptionistService.register(receptionistToRegister).subscribe(registeredReceptionist => {
        console.log(registeredReceptionist);
        this.receptionists.push(registeredReceptionist);
        console.log(this.receptionists);
      });
    });
  }

  get getReceptionists() {
    return this.receptionists;
  }

  showReceptionistDetails(receptionist: ReceptionistInfo) {

  }
}
