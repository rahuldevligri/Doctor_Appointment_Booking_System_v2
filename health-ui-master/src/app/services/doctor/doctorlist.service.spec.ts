import { TestBed } from '@angular/core/testing';

import { DoctorListService } from './doctorlist.service';

describe('DoctorlistService', () => {
  let service: DoctorListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctorListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
