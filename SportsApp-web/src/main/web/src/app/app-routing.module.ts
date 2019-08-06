import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LandingComponent} from './landing/landing.component';
import { AboutComponent } from './landing/about/about.component';
import  { HomeComponent } from './landing/home/home.component';
import  { ContactComponent } from './landing/contact/contact.component';
import  { LoginComponent } from './landing/login/login.component';
import  { RegisterComponent } from './landing/register/register.component';
import  { UserComponent } from './user/user.component';
import  { ProfileComponent } from './user/profile/profile.component';
import  { HistoryComponent } from './user/history/history.component';
import  { BookingComponent } from './user/booking/booking.component';



const routes: Routes = [
  { path: 'landing', component: LandingComponent,
    children : [
      {path:'', redirectTo:'home',pathMatch:'full'},
      {path:'home',component : HomeComponent},
      {path:'about',component : AboutComponent},
      {path:'contact',component :ContactComponent},
      {path:'login',component :LoginComponent},
      {path:'register',component:RegisterComponent}
    ]

    
  },
  { path: 'user', component: UserComponent ,
    children : [
      {path:'',redirectTo:'booking',pathMatch:'full'},
      {path:'booking',component: BookingComponent},
      {path:'profile',component: ProfileComponent},
      {path:'history',component: HistoryComponent}
    ]
  },
  { path: '', redirectTo:'landing', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
