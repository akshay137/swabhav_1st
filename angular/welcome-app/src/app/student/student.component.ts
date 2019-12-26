import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})

export class StudentComponent implements OnInit {

  student: IStudent;
  students: IStudent[];

  imgMaxWidth = 30;
  imgMaxHeight = 30;

  constructor() {
    console.log('Inside constructor');
  }

  ngOnInit() {
    console.log('inside oninit');
    this.student = {
      id: 1,
      name: 'akshay',
      cgpa: 6,
      location: 'Mumbai',
      profile: 'assets/profile.png',
    };
  }

  loadManyStudents() {
    console.log('Loading many students');
    this.students = [
      this.student,
      {
        id: 3,
        name: 'should be red',
        cgpa: 2,
        location: 'Mumbai',
        profile: 'assets/profile.png'
      },
      {
        id: 4,
        name: 'should be yellow',
        cgpa: 4,
        location: 'Mumbai',
        profile: 'assets/profile.png'
      },
      {
        id: 5,
        name: 'should be green',
        cgpa: 8.2,
        location: 'Mumbai',
        profile: 'assets/profile.png'
      }
    ];
  }

  cgpaToGrade(cgpa) {
    // console.log(cgpa);
    if (cgpa >= 8)
      return 'bg-success';
    if (cgpa >= 4)
      return 'bg-warning';
    return 'bg-danger';
  }

}

interface IStudent {
  id: Number;
  name: String;
  cgpa: Number;
  location: String;
  profile: String;
  maxWidth?: Number;
  maxHeight?: Number;
};