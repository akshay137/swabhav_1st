import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blueball',
  templateUrl: './blueball.component.html',
  styleUrls: ['./blueball.component.css']
})
export class BlueballComponent implements OnInit {

  gameRunning: boolean;
  balls: IBall[];
  maxBalls: number;
  magickNumber: number;
  attempts: number;

  DEFAULT_COLOR = '#aaaaaa';
  FORWARD_COLOR = '#00ff00';
  BACKWARD_COLOR = '#ff0000';
  CORRECT_COLOR = '$0000ff';

  constructor() { }

  ngOnInit() {
    this.gameRunning = false;
    this.maxBalls = 50;
  }

  startGame() {
    this.gameRunning = true;
    this.balls = [];
    this.generateMagickNumber();
    this.generateBalls();
    this.attempts = 5;

    console.log('magickNumber:', this.magickNumber);
  }

  generateMagickNumber() {
    this.magickNumber = 1 + Math.floor(Math.random() * this.maxBalls);
  }

  generateBalls() {
    for (let i = 0; i < this.maxBalls; i++) {
      let color = (i + 1) == this.magickNumber
        ? this.CORRECT_COLOR : (i + 1) < this.magickNumber
          ? this.FORWARD_COLOR : this.BACKWARD_COLOR;
      this.balls.push({
        id: i + 1,
        bgColor: color,
        disabled: false,
        currentColor: this.DEFAULT_COLOR
      });
    }
  }

  guessBall(id) {
    console.log('clicked', id);
    let ball = this.balls[id - 1];
    // this.balls[id - 1].currentClass = `btn ${this.balls[id - 1].cssClass}`;
    ball.currentColor = ball.bgColor;
    ball.disabled = true;
    this.attempts--;
    this.checkGameResult(id);
  }

  checkGameResult(id) {
    if (id == this.magickNumber) {
      this.endGame('Won')
    }
    else if (this.attempts == 0) {
      this.endGame('Lost');
    }
  }

  endGame(msg) {
    alert(`You ${msg}!\nAttmpes remainig: ${this.attempts}`);
    this.gameRunning = false;
    this.balls = [];
  }

}

interface IBall {
  id: number;
  bgColor: String;
  currentColor: String;
  disabled: boolean;
}