import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorInfoDialogComponent } from './doctor-info-dialog.component';

describe('DoctorInfoDialogComponent', () => {
  let component: DoctorInfoDialogComponent;
  let fixture: ComponentFixture<DoctorInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorInfoDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
