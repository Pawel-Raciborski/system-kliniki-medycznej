import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentPatientDiseaseComponent } from './appointment-patient-disease.component';

describe('AppointmentPatientDiseaseComponent', () => {
  let component: AppointmentPatientDiseaseComponent;
  let fixture: ComponentFixture<AppointmentPatientDiseaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentPatientDiseaseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentPatientDiseaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
