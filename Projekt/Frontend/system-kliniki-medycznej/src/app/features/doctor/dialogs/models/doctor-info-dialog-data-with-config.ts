import {DoctorInfo} from '../../domain/doctor-info';

export interface DoctorInfoDialogDataWithConfig {
  doctorInfo: DoctorInfo;
  showCreateAppointmentButton: boolean;
}
