import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicAppointmentDetailsDialogComponent } from './basic-appointment-details-dialog.component';

describe('BasicAppoitmentDetailsDialogComponent', () => {
  let component: BasicAppointmentDetailsDialogComponent;
  let fixture: ComponentFixture<BasicAppointmentDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BasicAppointmentDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BasicAppointmentDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
