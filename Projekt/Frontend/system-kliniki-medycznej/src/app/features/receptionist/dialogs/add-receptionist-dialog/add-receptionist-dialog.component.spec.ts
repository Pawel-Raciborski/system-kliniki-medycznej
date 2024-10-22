import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReceptionistDialogComponent } from './add-receptionist-dialog.component';

describe('AddReceptionistDialogComponent', () => {
  let component: AddReceptionistDialogComponent;
  let fixture: ComponentFixture<AddReceptionistDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddReceptionistDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddReceptionistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
