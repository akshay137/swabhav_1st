import { Component, OnInit } from '@angular/core';

import { MathService } from '../services/math.service';

@Component({
  selector: 'app-service-test',
  templateUrl: './service-test.component.html',
  styleUrls: ['./service-test.component.css']
})
export class ServiceTestComponent implements OnInit {

  constructor(private mathsvc: MathService) { }

  ngOnInit() {
    console.log(this.mathsvc.cubeEven(4));
    console.log(this.mathsvc.cubeEven(3));
  }

}
