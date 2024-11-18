import { TestBed } from '@angular/core/testing';

import { PatientDiseaseService } from './patient-disease.service';

describe('PatientDiseaseService', () => {
  let service: PatientDiseaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientDiseaseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
