import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedSearchOptionsComponent } from './advanced-search-options.component';

describe('AdvancedSearchOptionsComponent', () => {
  let component: AdvancedSearchOptionsComponent;
  let fixture: ComponentFixture<AdvancedSearchOptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdvancedSearchOptionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdvancedSearchOptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
