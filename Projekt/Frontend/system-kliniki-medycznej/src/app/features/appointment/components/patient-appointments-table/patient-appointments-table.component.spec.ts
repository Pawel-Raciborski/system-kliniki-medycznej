import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientAppointmentsTableComponent } from './patient-appointments-table.component';

describe('PatientAppointmentsTableComponent', () => {
  let component: PatientAppointmentsTableComponent;
  let fixture: ComponentFixture<PatientAppointmentsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientAppointmentsTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientAppointmentsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
