import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {DoctorInfo} from '../domain/doctor-info';
import {DoctorDetails} from '../domain/doctor-details';
import {DoctorSearchOptions} from '../components/search-bar/model/doctor-search-options';
import {environment} from '../../../../environments/environment.dev';
import {RegisterDoctorForm} from '../domain/register-doctor-form';
import {Pagination} from '../../pagination/model/pagination';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private apiUrl = `${environment.serverUrl}/doctors`;

  constructor(private httpClient: HttpClient) {
  }

  getPagedDoctors(paginationOptions: Pagination): Observable<DoctorInfo[]> {
    return this.httpClient.get<DoctorInfo[]>(
      `${this.apiUrl}/all`,
      {
        params: {
          page: paginationOptions.page,
          pageSize: paginationOptions.pageSize
        }
      }
    );
  }

  findByPwzNumber(pwzNumber: number) {
    return this.httpClient.get<DoctorDetails>(
      `${this.apiUrl}/${pwzNumber}`
    );
  }

  getAppointmentDoctorDetails(appointmentId: string): Observable<DoctorInfo> {
    let doctorDetails: DoctorInfo = {
      id: 1,
      name: "Jan",
      surname: "Kowalski",
      email: "jan.kowalski@mail.com",
      pwzNumber: '123456',
      phoneNumber: '555-1234',
      description: 'Expert in cardiology with 15 years of experience.',
      profileImagePath: ''
    };
    return of(doctorDetails);
  }

  getDoctorsWithSpecifiedOptions(doctorSearchOptions: DoctorSearchOptions): Observable<DoctorInfo[]> {
    return this.httpClient.post<DoctorInfo[]>(
      `${this.apiUrl}/search`,
      {...doctorSearchOptions}
    );
  }

  create(doctorToRegister: RegisterDoctorForm, profileImage: File | null): Observable<DoctorInfo> {
    const formData = new FormData();
    if (profileImage !== null) {
      formData.append('profileImage', profileImage, profileImage.name);
    }
    formData.append('doctorToRegisterData', new Blob([JSON.stringify(doctorToRegister)], {type: 'application/json'}));

    let httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'multipart/form-data');

    return this.httpClient.post<DoctorInfo>(
      `${this.apiUrl}/create`,
      formData,
      {
        headers: httpHeaders
      }
    );
  }

  delete(pwzNumber: string) {
    return this.httpClient.delete(
      `${this.apiUrl}/delete`,
      {
        params: {
          pwzNumber: pwzNumber
        },
        observe: 'response'
      }
    );
  }

  findById(id: number): Observable<DoctorDetails> {
    return this.httpClient.get<DoctorDetails>(
      `${this.apiUrl}/${id}/details`
    );
  }
}
