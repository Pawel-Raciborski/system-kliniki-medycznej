import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorSearchBarComponent } from './doctor-search-bar.component';

describe('DoctorSearchBarComponent', () => {
  let component: DoctorSearchBarComponent;
  let fixture: ComponentFixture<DoctorSearchBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DoctorSearchBarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DoctorSearchBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
