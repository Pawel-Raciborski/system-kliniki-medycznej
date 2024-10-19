import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfficeHoursDetailsDialogComponent } from './office-hours-details-dialog.component';

describe('OfficeHoursDetailsDialogComponent', () => {
  let component: OfficeHoursDetailsDialogComponent;
  let fixture: ComponentFixture<OfficeHoursDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfficeHoursDetailsDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OfficeHoursDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
