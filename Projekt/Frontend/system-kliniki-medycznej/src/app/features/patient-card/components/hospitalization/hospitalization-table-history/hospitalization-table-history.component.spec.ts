import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HospitalizationTableHistoryComponent } from './hospitalization-table-history.component';

describe('HospitalizationTableHistoryComponent', () => {
  let component: HospitalizationTableHistoryComponent;
  let fixture: ComponentFixture<HospitalizationTableHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HospitalizationTableHistoryComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HospitalizationTableHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
