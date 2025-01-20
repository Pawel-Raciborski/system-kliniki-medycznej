import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateHospitalizationDialogComponent } from './update-hospitalization-dialog.component';

describe('UpdateHospitalizationDialogComponent', () => {
  let component: UpdateHospitalizationDialogComponent;
  let fixture: ComponentFixture<UpdateHospitalizationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateHospitalizationDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateHospitalizationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
