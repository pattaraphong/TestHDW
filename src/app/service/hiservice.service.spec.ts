import { TestBed, inject } from '@angular/core/testing';

import { HiserviceService } from './hiservice.service';

describe('HiserviceService', () => {
  let service: HiserviceService;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HiserviceService]
    });
  });

  it('should be created',inject([HiserviceService], (service: HiserviceService) => {
    expect(service).toBeTruthy();
  }));
});
