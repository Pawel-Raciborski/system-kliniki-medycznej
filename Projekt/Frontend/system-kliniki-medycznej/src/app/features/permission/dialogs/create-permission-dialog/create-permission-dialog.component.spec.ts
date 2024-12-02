import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePermissionDialogComponent } from './create-permission-dialog.component';

describe('CreatePermissionDialogComponent', () => {
  let component: CreatePermissionDialogComponent;
  let fixture: ComponentFixture<CreatePermissionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreatePermissionDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatePermissionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
