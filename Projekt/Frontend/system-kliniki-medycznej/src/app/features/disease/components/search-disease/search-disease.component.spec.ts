import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchDiseaseComponent } from './search-disease.component';

describe('SearchDiseaseComponent', () => {
  let component: SearchDiseaseComponent;
  let fixture: ComponentFixture<SearchDiseaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchDiseaseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchDiseaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
