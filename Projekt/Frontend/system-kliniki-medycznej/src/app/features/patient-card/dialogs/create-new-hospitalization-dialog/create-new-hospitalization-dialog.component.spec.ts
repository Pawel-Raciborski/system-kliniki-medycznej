import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewHospitalizationDialogComponent } from './create-new-hospitalization-dialog.component';

describe('CreateNewHospitalizationDialogComponent', () => {
  let component: CreateNewHospitalizationDialogComponent;
  let fixture: ComponentFixture<CreateNewHospitalizationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateNewHospitalizationDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateNewHospitalizationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
