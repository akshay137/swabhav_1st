import { Customer, Address } from './customer'

let alexa: Customer = new Customer(1, 'alexa');
let siri: Customer = new Customer(2, 'siri');
let cortana: Customer = new Customer(3, 'cortana');

let mumbai: Address = new Address('Mumbai');

console.log(alexa.ID, alexa.Name);
console.log(siri.ID, siri.Name);
console.log(cortana.ID, cortana.Name);
console.log(mumbai.Address);