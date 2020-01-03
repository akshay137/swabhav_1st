import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
	{ path: '', redirectTo: 'home', pathMatch: 'full' },
	{ path: 'home', loadChildren: () => import('./home/home.module').then(m => m.HomePageModule) },
	{
		path: 'edit/:id',
		loadChildren: () => import('./edit/edit.module').then(m => m.EditPageModule)
	},
	{
		path: 'new',
		loadChildren: () => import('./new/new.module').then(m => m.NewPageModule)
	},
];

@NgModule({
	imports: [
		RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
	],
	exports: [RouterModule]
})
export class AppRoutingModule { }
