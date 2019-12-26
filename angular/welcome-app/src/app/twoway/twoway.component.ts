import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-twoway',
  templateUrl: './twoway.component.html',
  styleUrls: ['./twoway.component.css']
})
export class TwowayComponent implements OnInit {

  firstname: String;
  lastname: String;

  constructor() { }

  ngOnInit() {
    this.firstname = 'swabhav';
    this.lastname = 'techlabs';
  }

  firstnameChange(e) {
    console.log(e);
    this.firstname = e.toUpperCase();
  }

}
