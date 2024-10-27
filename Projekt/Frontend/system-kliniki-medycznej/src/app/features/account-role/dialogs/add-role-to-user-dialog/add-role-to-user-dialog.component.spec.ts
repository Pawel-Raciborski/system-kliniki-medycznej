import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRoleToUserDialogComponent } from './add-role-to-user-dialog.component';

describe('AddRoleToUserDialogComponent', () => {
  let component: AddRoleToUserDialogComponent;
  let fixture: ComponentFixture<AddRoleToUserDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddRoleToUserDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddRoleToUserDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
