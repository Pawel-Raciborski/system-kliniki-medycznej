import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalizationDetailsComponent } from './hospitalization-details.component';

describe('HospitalizationDetailsComponent', () => {
  let component: HospitalizationDetailsComponent;
  let fixture: ComponentFixture<HospitalizationDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HospitalizationDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HospitalizationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
