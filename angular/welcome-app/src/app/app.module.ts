import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { SnakeCase } from './pipes/snakecase';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './Welcome/welcome.component';
import { StudentComponent } from './student/student.component';
import { BlueballComponent } from './blueball/blueball.component';
import { TwowayComponent } from './twoway/twoway.component';
import { ServiceTestComponent } from './service-test/service-test.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    StudentComponent,
    BlueballComponent,
    TwowayComponent,
    SnakeCase,
    ServiceTestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [ServiceTestComponent]
})
export class AppModule { }
