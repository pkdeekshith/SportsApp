import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LandingComponent} from './landing/landing.component';
import { AboutComponent } from './landing/about/about.component';
import  { HomeComponent } from './landing/home/home.component';
import  { ContactComponent } from './landing/contact/contact.component';
import  { LoginComponent } from './landing/login/login.component';
import  { RegisterComponent } from './landing/register/register.component';
import  { PreRegisterComponent } from './landing/pre-register/pre-register.component';

import  { UserComponent } from './user/user.component';
import  { ProfileComponent } from './user/profile/profile.component';
import  { HistoryComponent } from './user/history/history.component';
import  { BookingComponent } from './user/booking/booking.component';
import  { SportComponent } from './user/sport/sport.component';

import  { BookComponent } from './user/book/book.component';

import {AdminComponent} from './admin/admin.component';
import {SettingsComponent} from './admin/settings/settings.component';
import {ReportsComponent} from './admin/reports/reports.component';
import { FacilityComponent } from './landing/facility/facility.component';
import { PrivacyComponent } from './landing/privacy/privacy.component';
import { FaqComponent } from './landing/faq/faq.component';

const routes: Routes = [
  { path: 'landing', component: LandingComponent,
    children : [
      {path:'', redirectTo:'home',pathMatch:'full'},
      {path:'facility',component: FacilityComponent},
      {path:'home',component : HomeComponent},
      {path:'about',component : AboutComponent},
      {path:'contact',component :ContactComponent},
      {path:'login',component :LoginComponent},
      {path:'validate',component : PreRegisterComponent},
      {path:'register',component:RegisterComponent},
      {path:'privacy',component:PrivacyComponent},
      {path:'faq',component:FaqComponent}
    ]

    
  },
  { path: 'user', component: UserComponent ,
    children : [
      {path:'',redirectTo:'sport',pathMatch:'full'},
      {path:'sport',component: SportComponent},
      {path:'booking',component: BookingComponent},
      {path:'book',component: BookComponent},
      {path:'profile',component: ProfileComponent},
      {path:'history',component: HistoryComponent}
    ]
  },
  { path: 'admin', component: AdminComponent,
    children : [
      {path:'',redirectTo:'reports',pathMatch:'full'},
      {path:'settings',component: SettingsComponent},
      {path:'reports',component: ReportsComponent}
    ]
  },
  { path: '', redirectTo:'landing', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
