import { Component, OnInit, NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface History {
  id: number;
  historyDateTime: string;
  description: string;
}
export interface Tag {
  id: number;
  name: string;
}

export interface Bookmark {
  id: number;
  name: string;
  url: string;
  savedUser: string;
  lastclick: string;
  clickcount: number;
  tags: Tag[];
  history: History[];
}

@Component({
  selector: 'app-bookmarkpage',
  templateUrl: './bookmarkpage.component.html',
  styleUrls: ['./bookmarkpage.component.css']
})

export class BookmarkpageComponent implements OnInit {

  bookmarkCount = 0;
  bookmarkObjects: any;
  url = 'http://localhost:8083/all';
  // url = 'http://localhost:8085/bookmark-database/all';


  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get(this.url).toPromise().then(res => {
      console.log(res);
      this.bookmarkCount = Object.keys(res).length;
      this.bookmarkObjects = res;
    });
  }
}
