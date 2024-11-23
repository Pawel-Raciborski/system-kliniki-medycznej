import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoundMedicinesComponent } from './found-medicines.component';

describe('FoundMedicinesComponent', () => {
  let component: FoundMedicinesComponent;
  let fixture: ComponentFixture<FoundMedicinesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoundMedicinesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoundMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
