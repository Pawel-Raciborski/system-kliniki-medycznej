import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCreatingPrescriptionDetailsDialogComponent } from './show-creating-prescription-details-dialog.component';

describe('ShowCreatingPrescriptionDetailsDialogComponent', () => {
  let component: ShowCreatingPrescriptionDetailsDialogComponent;
  let fixture: ComponentFixture<ShowCreatingPrescriptionDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowCreatingPrescriptionDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowCreatingPrescriptionDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
