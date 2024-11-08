import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorOfficeHoursComponent } from './doctor-office-hours.component';

describe('DoctorOfficeHoursComponent', () => {
  let component: DoctorOfficeHoursComponent;
  let fixture: ComponentFixture<DoctorOfficeHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorOfficeHoursComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorOfficeHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
