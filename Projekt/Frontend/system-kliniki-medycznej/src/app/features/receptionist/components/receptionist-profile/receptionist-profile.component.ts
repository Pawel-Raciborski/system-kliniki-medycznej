import {Component, Input, OnInit} from '@angular/core';
import {HeaderTitleComponent} from '../../../header-title/header-title.component';
import {
  PersonalDetailsComponent
} from '../../../personal-details/components/personal-details/personal-details.component';
import {ReceptionistDetails} from '../../model/receptionist-details';
import {ReceptionistService} from '../../services/receptionist.service';
import {AccountComponent} from '../../../account/components/account/account.component';
import {AccountInfo} from '../../../account/model/account-info';

@Component({
  selector: 'app-receptionist-profile',
  standalone: true,
  imports: [
    HeaderTitleComponent,
    PersonalDetailsComponent,
    AccountComponent
  ],
  templateUrl: './receptionist-profile.component.html',
  styleUrl: './receptionist-profile.component.css'
})
export class ReceptionistProfileComponent implements OnInit {
  @Input() id!: number;
  public receptionist!: ReceptionistDetails;

  constructor(private receptionistService: ReceptionistService) {

  }

  ngOnInit(): void {
    this.receptionistService.findById(this.id).subscribe(receptionist => {
      this.receptionist = receptionist;
    })
  }


  get getAccountInfo(): AccountInfo {
    return {
      username: this.receptionist.username,
      email: this.receptionist.email
    };
  }
}
