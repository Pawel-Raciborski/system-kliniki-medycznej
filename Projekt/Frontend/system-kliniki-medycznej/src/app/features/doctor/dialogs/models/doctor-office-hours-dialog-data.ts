import {FormGroup} from '@angular/forms';
import {OfficeHours} from '../../domain/office-hours';

export interface DoctorOfficeHoursDialogData {
  officeHours?: OfficeHours;
  isNewData: boolean
}
