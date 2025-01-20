import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddHospitalizationDialogComponent } from './add-hospitalization-dialog.component';

describe('AddHospitalizationDialogComponent', () => {
  let component: AddHospitalizationDialogComponent;
  let fixture: ComponentFixture<AddHospitalizationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddHospitalizationDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddHospitalizationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
