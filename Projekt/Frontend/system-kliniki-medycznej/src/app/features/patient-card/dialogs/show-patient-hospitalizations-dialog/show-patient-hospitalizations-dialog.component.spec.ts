import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowPatientHospitalizationsDialogComponent } from './show-patient-hospitalizations-dialog.component';

describe('ShowPatientHospitalizationsDialogComponent', () => {
  let component: ShowPatientHospitalizationsDialogComponent;
  let fixture: ComponentFixture<ShowPatientHospitalizationsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowPatientHospitalizationsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowPatientHospitalizationsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
