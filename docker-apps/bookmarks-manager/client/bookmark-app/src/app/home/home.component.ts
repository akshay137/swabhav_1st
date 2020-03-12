import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { StorageService, STORAGE } from '../services/storage.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	private passType: string = 'password';

	private loginForm: FormGroup;
	private registerForm: FormGroup;

	constructor(
		private auth: AuthService,
		private formBuilder: FormBuilder,
		private storage: StorageService,
		private router: Router
	) { }

	ngOnInit() {
		this.buildLoginForm();
		this.buildRegisterForm();
	}

	buildLoginForm() {
		this.loginForm = this.formBuilder.group({
			email: new FormControl('', Validators.compose([
				Validators.required,
				Validators.email
			])),
			password: new FormControl('', Validators.compose([
				Validators.required
			]))
		});
	}

	buildRegisterForm() {
		this.registerForm = this.formBuilder.group({
			name: new FormControl('', Validators.compose([
				Validators.required
			])),
			email: new FormControl('', Validators.compose([
				Validators.required,
				Validators.email
			])),
			password: new FormControl('', Validators.compose([
				Validators.required
			])),
			confirmPassword: new FormControl('')
		});
	}

	isRegisterFormValid() {
		return this.registerForm.valid
			&& (this.registerForm.value.password
				== this.registerForm.value.confirmPassword)
	}

	login() {
		console.log('logging in', this.loginForm.value)
		const data = this.loginForm.value;
		this.auth.login(data.email, data.password).then(res => {
			console.log('login', res)
			this.storage.setCache(STORAGE.USER_ID, res)
			this.router.navigate(['/dashboard'])
		}).catch(err => {
			console.log('error', err.error)
		})
	}

	register() {
		console.log('registering')
		const data = this.registerForm.value;
		this.auth.register(data.name, data.email, data.password)
			.then(res => {
				console.log(res, typeof (res))
				this.storage.setCache(STORAGE.USER_ID, res)
				this.router.navigate(['/dashboard'])
			}).catch(err => {
				console.log(err.error)
			})
	}

}
