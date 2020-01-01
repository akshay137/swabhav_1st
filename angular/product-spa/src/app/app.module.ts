import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { StarComponent } from './star/star.component';
import { ProductListComponent } from './productlist/productlist.component';
import { ProductDetailComponent } from './productdetail/productdetail.component';
import { LoadingViewComponent } from './loading-view/loading-view.component';

import { FilterByName } from './filter-name.pipe';

@NgModule({
	declarations: [
		AppComponent,
		WelcomeComponent,
		StarComponent,
		ProductListComponent,
		ProductDetailComponent,
		LoadingViewComponent,
		FilterByName
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		FormsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule { }
