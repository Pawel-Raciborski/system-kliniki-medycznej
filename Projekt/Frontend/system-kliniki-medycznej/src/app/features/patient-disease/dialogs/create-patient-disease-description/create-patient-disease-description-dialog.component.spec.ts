import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePatientDiseaseDescriptionDialogComponent } from './create-patient-disease-description-dialog.component';

describe('CreatePatientDiseaseDescriptionComponent', () => {
  let component: CreatePatientDiseaseDescriptionDialogComponent;
  let fixture: ComponentFixture<CreatePatientDiseaseDescriptionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePatientDiseaseDescriptionDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePatientDiseaseDescriptionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
