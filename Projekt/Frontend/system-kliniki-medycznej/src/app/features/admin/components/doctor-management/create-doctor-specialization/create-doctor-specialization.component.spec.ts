import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateDoctorSpecializationComponent } from './create-doctor-specialization.component';

describe('CreateDoctorSpecializationComponent', () => {
  let component: CreateDoctorSpecializationComponent;
  let fixture: ComponentFixture<CreateDoctorSpecializationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateDoctorSpecializationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateDoctorSpecializationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
