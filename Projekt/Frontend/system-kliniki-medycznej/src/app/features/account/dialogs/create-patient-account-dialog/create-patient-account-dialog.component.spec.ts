import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePatientAccountDialogComponent } from './create-patient-account-dialog.component';

describe('CreatePatientAccountDialogComponent', () => {
  let component: CreatePatientAccountDialogComponent;
  let fixture: ComponentFixture<CreatePatientAccountDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePatientAccountDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePatientAccountDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
