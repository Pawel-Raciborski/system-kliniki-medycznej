import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorPatientCardComponent } from './doctor-patient-card.component';

describe('DoctorPatientCardComponent', () => {
  let component: DoctorPatientCardComponent;
  let fixture: ComponentFixture<DoctorPatientCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorPatientCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorPatientCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
