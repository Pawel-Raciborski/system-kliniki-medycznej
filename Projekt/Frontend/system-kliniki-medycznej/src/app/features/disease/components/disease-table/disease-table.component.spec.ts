import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiseaseTableComponent } from './disease-table.component';

describe('DiseaseTableComponent', () => {
  let component: DiseaseTableComponent;
  let fixture: ComponentFixture<DiseaseTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiseaseTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiseaseTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
