import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { routeArgs } from './RouteConfig';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { CareerComponent } from './career/career.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { DepartmentComponent } from './department/department.component';

@NgModule({
	declarations: [
		AppComponent,
		HomeComponent,
		AboutComponent,
		CareerComponent,
		NotfoundComponent,
		DepartmentComponent
	],
	imports: [
		BrowserModule,
		RouterModule.forRoot(routeArgs),
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
