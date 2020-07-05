import { Component, OnInit } from '@angular/core';
import {HeaderComponent} from '../template/header/header.component';
import {FooterComponent} from '../template/footer/footer.component';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import {Utility} from '../shared/utility/utility';
import { BackendService} from '../shared/service/backend.service';
import Cookies from 'js-cookie';
import { NgxUiLoaderService } from 'ngx-ui-loader';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private Router:Router,private BackendService:BackendService,private Utility:Utility,private ngxService: NgxUiLoaderService) {
   if(window.location.pathname == "/landing/register" || window.location.pathname == "/landing/validate"){
      if( this.BackendService.editMode != true){
        Utility.deleteSession();
        this.BackendService.editMode = false;
        this.Router.navigateByUrl("/landing/login");
      }
   }
   const tok = localStorage.getItem("auth-token");
   const orderId = localStorage.getItem("orderId");
   if(tok && orderId){
    this.ngxService.start();
     this.BackendService.getMemberCred({tok,orderId}).subscribe(
       data=>{
         this.ngxService.stop();
         if(data && data.status =="S"){
           this.BackendService.memberId = data.memberId;
           this.BackendService.memberRole = 'user';
           localStorage.clear();
           this.Router.navigateByUrl("/user/history");
         }
       },error=>{
        this.ngxService.stop();
       }
     )
   }
   }

  ngOnInit() {
  }

}
