import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NumberapiComponent } from './numberapi.component';

describe('NumberapiComponent', () => {
  let component: NumberapiComponent;
  let fixture: ComponentFixture<NumberapiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NumberapiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NumberapiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
