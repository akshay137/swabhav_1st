import { Component, OnInit } from '@angular/core';

import { MathService } from '../services/math.service';
import { LoggerService } from '../services/logger.service';

@Component({
	selector: 'app-service-test',
	templateUrl: './service-test.component.html',
	styleUrls: ['./service-test.component.css']
})
export class ServiceTestComponent implements OnInit {

	constructor(private mathsvc: MathService, private logsvc: LoggerService) { }

	ngOnInit() {
		console.log(this.mathsvc.cubeEven(4));
		console.log(this.mathsvc.cubeEven(3));
		this.logsvc.log('log from loggerservice');
		let l: LoggerService = new LoggerService();
		l.log('second instance of logger');
	}

}
