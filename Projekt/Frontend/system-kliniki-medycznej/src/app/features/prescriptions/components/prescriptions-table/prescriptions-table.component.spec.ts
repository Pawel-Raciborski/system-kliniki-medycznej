import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrescriptionsTableComponent } from './prescriptions-table.component';

describe('PrescriptionsTableComponent', () => {
  let component: PrescriptionsTableComponent;
  let fixture: ComponentFixture<PrescriptionsTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrescriptionsTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrescriptionsTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
