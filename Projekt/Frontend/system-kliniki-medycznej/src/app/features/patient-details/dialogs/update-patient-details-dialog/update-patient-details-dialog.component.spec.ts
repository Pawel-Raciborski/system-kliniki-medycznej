import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePatientDetailsDialogComponent } from './update-patient-details-dialog.component';

describe('UpdatePatientDetailsDialogComponent', () => {
  let component: UpdatePatientDetailsDialogComponent;
  let fixture: ComponentFixture<UpdatePatientDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePatientDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatePatientDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
