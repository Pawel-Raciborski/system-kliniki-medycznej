import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceptionistDoctorsAppointmentsCalendarComponent } from './receptionist-doctors-appointments-calendar.component';

describe('ReceptionistDoctorsAppointmentsCalendarComponent', () => {
  let component: ReceptionistDoctorsAppointmentsCalendarComponent;
  let fixture: ComponentFixture<ReceptionistDoctorsAppointmentsCalendarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReceptionistDoctorsAppointmentsCalendarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceptionistDoctorsAppointmentsCalendarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
