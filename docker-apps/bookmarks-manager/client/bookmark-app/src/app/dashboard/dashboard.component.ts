import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { StorageService, STORAGE } from '../services/storage.service';
import { BookmarksService } from '../services/bookmarks.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

declare let $;

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	private user: any;
	private recentBookmarks: Array<any>;
	private categories: Array<any>;
	// private bookmarks: Map<string, Array<any>>;
	private currentBms: Array<any>;
	private currentCat: string;

	private catForm: FormGroup;
	private bmForm: FormGroup;

	constructor(
		private auth: AuthService,
		private bookmarksvc: BookmarksService,
		private storage: StorageService,
		private formBuilder: FormBuilder,
		private router: Router
	) { }

	ngOnInit() {
		this.loadUserData();
		// this.bookmarks = new Map<string, Array<any>>();
		this.buildCategoryForm();
		this.buildBookmarkForm();
	}

	redirect(url: string): void {
		if (!url.startsWith('http')) {
			window.open(`http://${url}`);
		} else {
			window.open(url);
		}
	}

	loadBookmarks(event) {
		// console.log(event, event.target.value);
		this.currentCat = event.target.value;
		this.getBookmarks(event.target.value);
	}

	buildCategoryForm() {
		this.catForm = this.formBuilder.group({
			user_id: new FormControl({
				value: '',
				disabled: true
			}, Validators.compose([])),
			name: new FormControl('', Validators.compose([
				Validators.required
			]))
		});
	}

	buildBookmarkForm() {
		this.bmForm = this.formBuilder.group({
			name: new FormControl('', Validators.compose([
				Validators.required
			])),
			url: new FormControl('', Validators.compose([
				Validators.required
			])),
			category: new FormControl({ value: '', disabled: true },
				Validators.compose([
					Validators.required
				]))
		});
	}

	logout() {
		this.storage.setCache(STORAGE.USER_ID, '');
		this.router.navigate(['/home'], { replaceUrl: true });
	}

	loadUserData() {
		let user_id = this.storage.getCache(STORAGE.USER_ID)
		if (!user_id
			|| typeof (user_id) != 'string'
			|| user_id.length == 0) {
			// go back
			this.logout();
			return;
		}

		this.auth.getUser(user_id).then(res => {
			console.log(res);
			this.user = res;
			this.catForm.controls['user_id'].setValue(this.user.id);
			this.getCategories();
			this.getRecentBookmarks();
		}).catch(err => {
			console.log(err);
			this.logout();
			alert(err.error);
		})
	}

	getRecentBookmarks() {
		// console.log(this.bookmarksvc);
		this.bookmarksvc.getRecent(this.user.id).then(res => {
			this.recentBookmarks = res;
			console.log('recent', this.recentBookmarks);
		}).catch(err => {
			console.log('err with recent bookmarks', err.error)
		});
	}

	getCategories() {
		this.bookmarksvc.getCategories(this.user.id).then(res => {
			this.categories = res;
			if (res && res.length > 0) {
				this.currentCat = res[0].id;
				this.getBookmarks(res[0].id);
			}
			console.log('cats', this.categories);
		}).catch(err => {
			console.log('err with cats', err.error)
		})
	}

	getBookmarks(cat_id: string) {
		this.currentBms = [];
		this.bookmarksvc.getBookmarks(this.user.id, cat_id).then(res => {
			// this.bookmarks.set(cat_id, res);
			this.currentBms = res;
			console.log('bookmarks', this.currentBms);
		}).catch(err => {
			console.log('err with bookmarks', err.error)
		});
	}

	showAddCategory() {
		this.catForm.reset();
		this.catForm.controls['user_id'].setValue(this.user.id);
		$('#category-modal').modal('show');
	}

	showEditCategory() {
		this.catForm.reset();
		this.catForm.controls['user_id'].setValue(this.user.id);
		this.catForm.controls['name'].setValue(
			this.findInArray(this.categories, 'id', this.currentCat).name);
		$('#category-modal').modal('show');
	}

	showAddBookmark(cat_id: string) {
		if (!this.categories || this.categories.length == 0) {
			alert("Please add category first");
			return;
		}
		if (!cat_id)
			cat_id = this.currentCat;
		this.bmForm.reset();
		this.bmForm.controls['category'].setValue(cat_id);
		$('#bookmark-modal').modal('show');
	}

	addCategory() {
		const data = this.catForm.getRawValue();
		this.bookmarksvc.addCategory(this.user.id, data.name)
			.then(res => {
				console.log('added a new cat', res);
				this.getCategories();
			}).catch(err => {
				console.log('error adding a new cat', err.error);
			}).finally(() => {
				$('#category-modal').modal('hide');
				this.catForm.reset();
			});
	}

	deleteCategory() {
		if (!confirm('Are you sure?'))
			return;
		this.bookmarksvc.deleteCategory(this.user.id, this.currentCat)
			.then(res => {
				console.log('deleted a cat', res);
				this.getCategories();
			}).catch(err => {
				console.log('err deleting cat', err.error);
			});
	}

	addBookmark() {
		const data = this.bmForm.getRawValue();
		// console.log('data', data);
		this.bookmarksvc.addBookmark(this.user.id,
			data.name, data.url, data.category).then(res => {
				console.log('added a new bookmark to the cat', res);
				this.getRecentBookmarks();
				this.getBookmarks(data.category);
			}).catch(err => {
				console.log('err in adding bookmark to a cat', err.error);
			}).finally(() => {
				$('#bookmark-modal').modal('hide');
				this.bmForm.reset();
			})
	}


	findInArray(arr: Array<any>, attr: string, value: any): any {
		return arr.find(e => e[attr] === value);
	}
}
