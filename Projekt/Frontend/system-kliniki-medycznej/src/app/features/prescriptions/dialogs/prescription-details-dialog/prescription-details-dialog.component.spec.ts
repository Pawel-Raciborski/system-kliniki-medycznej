import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionDetailsDialogComponent } from './prescription-details-dialog.component';

describe('PrescriptionDetailsDialogComponent', () => {
  let component: PrescriptionDetailsDialogComponent;
  let fixture: ComponentFixture<PrescriptionDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrescriptionDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
