import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EditComponent } from './edit/edit.component';
import { AddComponent } from './add/add.component';

export const routes: Routes = [
	{ path: 'home', component: HomeComponent },
	{ path: 'edit/:id', component: EditComponent },
	{ path: 'add', component: AddComponent },
	{ path: '**', component: HomeComponent },
	{ path: '', redirectTo: '/home', pathMatch: 'full' },
];