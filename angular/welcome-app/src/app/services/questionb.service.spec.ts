import { TestBed } from '@angular/core/testing';

import { QuestionbService } from './questionb.service';

describe('QuestionbService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QuestionbService = TestBed.get(QuestionbService);
    expect(service).toBeTruthy();
  });
});
