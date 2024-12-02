import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPermissionsToRoleDialogComponent } from './add-permissions-to-role-dialog.component';

describe('AddPermissionsToRoleDialogComponent', () => {
  let component: AddPermissionsToRoleDialogComponent;
  let fixture: ComponentFixture<AddPermissionsToRoleDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPermissionsToRoleDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPermissionsToRoleDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
