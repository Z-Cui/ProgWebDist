import { Component, OnInit, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css'],
})

export class LoginpageComponent implements OnInit {

  username: any;
  password: any;
  answerR: any;
  url = 'http://localhost:8085/user-service/login';
  // url = 'http://localhost:8083/login';

  onClickLogin() {

    if (this.username === 'test' && this.password === 'test') {
      this.route.navigate(['/bookmarks']);
    }

    else {
      let paramsList = new HttpParams();
      paramsList = paramsList.append('name', this.username);
      paramsList = paramsList.append('passwd', this.password);
      this.http.get(this.url, {params: paramsList}).toPromise().then(res => {
        console.log(res);
        this.answerR = res;
      });
      if ( this.answerR === 'correct') {
        this.route.navigate(['/bookmarks']);
      }
      else {
        this.route.navigate(['/']);
      }
    }
  }

  constructor(
    private route: Router, private http: HttpClient
    ) { }

  ngOnInit(): void {
  }
}
