import {Component, Input, OnInit} from '@angular/core';
import {HeaderTitleComponent} from '../../../header-title/header-title.component';
import {
  PersonalDetailsComponent
} from '../../../personal-details/components/personal-details/personal-details.component';
import {ReceptionistDetails} from '../../model/receptionist-details';
import {ReceptionistService} from '../../services/receptionist.service';
import {AccountComponent} from '../../../account/components/account/account.component';
import {AccountInfo} from '../../../account/model/account-info';
import {PersonalDetails} from '../../../personal-details/domain/personal-details';
import {AccountRolesComponent} from '../../../account-role/components/account-roles/account-roles.component';
import {UserService} from '../../../auth/services/user.service';
import {HttpStatusCode} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-receptionist-profile',
  standalone: true,
  imports: [
    HeaderTitleComponent,
    PersonalDetailsComponent,
    AccountComponent,
    AccountRolesComponent
  ],
  templateUrl: './receptionist-profile.component.html',
  styleUrl: './receptionist-profile.component.css'
})
export class ReceptionistProfileComponent implements OnInit {
  @Input() id!: number;
  public receptionist!: ReceptionistDetails;

  constructor(
    private receptionistService: ReceptionistService,
    public userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    ) {

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

  get getPersonalDetails(): PersonalDetails {
    return this.receptionist.personalDetails;
  }

  onDeleteReceptionist() {
    this.receptionistService.delete(this.id).subscribe(response => {
      if(response.status === HttpStatusCode.Ok){
        this.router.navigate(['../'],{relativeTo: this.activatedRoute});
      }
    })
  }
}
