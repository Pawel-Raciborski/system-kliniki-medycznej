import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorManagementPageComponent } from './doctor-management-page.component';

describe('DoctorManagementPageComponent', () => {
  let component: DoctorManagementPageComponent;
  let fixture: ComponentFixture<DoctorManagementPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorManagementPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
