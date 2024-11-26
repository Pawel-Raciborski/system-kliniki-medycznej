import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMedicineBarComponent } from './search-medicine-bar.component';

describe('SearchMedicineBarComponent', () => {
  let component: SearchMedicineBarComponent;
  let fixture: ComponentFixture<SearchMedicineBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchMedicineBarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchMedicineBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
