import { TestBed } from '@angular/core/testing';

import { AppoinmentSummaryService } from './appoinment-summary.service';

describe('AppoinmentSummaryService', () => {
  let service: AppoinmentSummaryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AppoinmentSummaryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
