import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoaderviewComponent } from './loaderview.component';

describe('LoaderviewComponent', () => {
  let component: LoaderviewComponent;
  let fixture: ComponentFixture<LoaderviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoaderviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoaderviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
