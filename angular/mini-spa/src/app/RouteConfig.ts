import { Routes } from '@angular/router'
import { HomeComponent } from './home/home.component';
import { CareerComponent } from './career/career.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { AboutComponent } from './about/about.component';
import { DepartmentComponent } from './department/department.component';

export const routeArgs: Routes = [
	{ path: 'home', component: HomeComponent },
	{ path: 'about', component: AboutComponent },
	{ path: 'career', component: CareerComponent },
	{ path: 'dept/:deptId', component: DepartmentComponent },
	{ path: '', redirectTo: '/home', pathMatch: 'full' },
	{ path: '**', component: NotfoundComponent },
];