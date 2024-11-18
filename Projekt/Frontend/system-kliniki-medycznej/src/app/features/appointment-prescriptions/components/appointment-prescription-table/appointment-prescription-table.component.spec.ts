import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentPrescriptionTableComponent } from './appointment-prescription-table.component';

describe('AppointmentPrescriptionTableComponent', () => {
  let component: AppointmentPrescriptionTableComponent;
  let fixture: ComponentFixture<AppointmentPrescriptionTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentPrescriptionTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentPrescriptionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
