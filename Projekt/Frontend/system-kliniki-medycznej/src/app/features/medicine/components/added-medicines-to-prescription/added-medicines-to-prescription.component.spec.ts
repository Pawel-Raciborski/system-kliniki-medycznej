import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddedMedicinesToPrescriptionComponent } from './added-medicines-to-prescription.component';

describe('AddedMedicinesToPrescriptionComponent', () => {
  let component: AddedMedicinesToPrescriptionComponent;
  let fixture: ComponentFixture<AddedMedicinesToPrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddedMedicinesToPrescriptionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddedMedicinesToPrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
