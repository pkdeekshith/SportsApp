import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from  '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import {TableModule} from 'primeng/table';
import {CalendarModule} from 'primeng/calendar';
import {MultiSelectModule} from 'primeng/multiselect';
import { NgxUiLoaderModule } from  'ngx-ui-loader';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {DialogModule} from 'primeng/dialog';
import {ToastModule} from 'primeng/toast';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing.component';
import { HeaderComponent } from './template/header/header.component';
import { FooterComponent } from './template/footer/footer.component';
import { AboutComponent } from './landing/about/about.component';
import { HomeComponent } from './landing/home/home.component';
import { ContactComponent } from './landing/contact/contact.component';
import { LoginComponent } from './landing/login/login.component';
import { RegisterComponent } from './landing/register/register.component';
import { FormsModule,ReactiveFormsModule }    from '@angular/forms';
import { UserComponent } from './user/user.component';
import { NavComponent } from './template/nav/nav.component';
import { ProfileComponent } from './user/profile/profile.component';
import { HistoryComponent } from './user/history/history.component';
import { BookingComponent } from './user/booking/booking.component';
import { PaymentComponent } from './user/payment/payment.component';
import {Config} from './shared/constant/config';
import {Data} from './shared/data/data';
import {MessageService} from 'primeng/api';


@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    HeaderComponent,
    FooterComponent,
    AboutComponent,
    HomeComponent,
    ContactComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    NavComponent,
    ProfileComponent,
    HistoryComponent,
    BookingComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    TableModule,
    CalendarModule,
    MultiSelectModule,
    BrowserAnimationsModule,
    NgxUiLoaderModule,
    MessagesModule,
    MessageModule,
    DialogModule,
    ToastModule

  ],
  providers: [Config,MessageService,Data],
  exports:[ FormsModule,
    ReactiveFormsModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
