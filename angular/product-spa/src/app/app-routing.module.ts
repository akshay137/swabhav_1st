import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { ProductListComponent } from './productlist/productlist.component';
import { ProductDetailComponent } from './productdetail/productdetail.component';


const routes: Routes = [
	{ path: 'home', component: WelcomeComponent },
	{ path: 'list', component: ProductListComponent },
	{ path: 'product/:id', component: ProductDetailComponent },
	{ path: '**', component: WelcomeComponent },
	{ path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
