import { Injectable } from '@angular/core';

import { LoggerService } from './logger.service'

@Injectable({
  providedIn: 'root'
})
export class MathService {

  constructor(private loggersvc: LoggerService) { }

  cubeEven(n): number {
    if (n % 2 != 0) {
      this.loggersvc.log(`Number ${n} is not even.`);
      // throw "Number not even";
      return null;
    }
    return n * n * n;
  }
}
