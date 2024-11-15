import { TestBed } from '@angular/core/testing';

import { PatientCardService } from './patient-card.service';

describe('PatientCardService', () => {
  let service: PatientCardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientCardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
