import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { SnakeCase } from './pipes/snakecase';
import { OddOrAll } from './pipes/oddOrAll';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './Welcome/welcome.component';
import { StudentComponent } from './student/student.component';
import { BlueballComponent } from './blueball/blueball.component';
import { TwowayComponent } from './twoway/twoway.component';
import { ServiceTestComponent } from './service-test/service-test.component';
import { NumberapiComponent } from './numberapi/numberapi.component';
import { ParentchildComponent } from './parentchild/parentchild.component';
import { ToggleComponent } from './parentchild/toggle/toggle.component';
import { StarComponent } from './parentchild/starrating/star.component';
import { QuestionbankComponent } from './questionbank/questionbank.component';

@NgModule({
	declarations: [
		AppComponent,
		WelcomeComponent,
		StudentComponent,
		BlueballComponent,
		TwowayComponent,
		SnakeCase,
		OddOrAll,
		ServiceTestComponent,
		NumberapiComponent,
		ParentchildComponent,
		ToggleComponent,
		StarComponent,
		QuestionbankComponent
	],
	imports: [
		BrowserModule,
		FormsModule,
		HttpClientModule,
	],
	providers: [],
	bootstrap: [QuestionbankComponent]
})
export class AppModule { }
