export class Customer {
	constructor(private _id, private _name) { }

	get ID() {
		return this._id;
	}

	get Name() {
		return this._name;
	}

	set Name(name) {
		this._name = name;
	}
}

export class Address {
	constructor(private _address) { }

	get Address() {
		return this._address;
	}
}