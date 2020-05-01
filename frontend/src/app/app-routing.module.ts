import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginpageComponent } from './loginpage/loginpage.component';
import { BookmarkpageComponent } from './bookmarkpage/bookmarkpage.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'bookmarks',
    component: BookmarkpageComponent
  },
  {
    path: 'login',
    component: LoginpageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
