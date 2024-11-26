import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPrescriptionMedicinesDialogComponent } from './add-prescription-medicines-dialog.component';

describe('CreatePrescriptionDialogComponent', () => {
  let component: AddPrescriptionMedicinesDialogComponent;
  let fixture: ComponentFixture<AddPrescriptionMedicinesDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPrescriptionMedicinesDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPrescriptionMedicinesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
