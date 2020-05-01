import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookmarkpageComponent } from './bookmarkpage.component';

describe('BookmarkpageComponent', () => {
  let component: BookmarkpageComponent;
  let fixture: ComponentFixture<BookmarkpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookmarkpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookmarkpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
