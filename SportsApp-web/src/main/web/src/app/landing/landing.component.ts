import { Component, OnInit } from '@angular/core';
import {HeaderComponent} from '../template/header/header.component';
import {FooterComponent} from '../template/footer/footer.component';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import {Utility} from '../shared/utility/utility';
import { BackendService} from '../shared/service/backend.service';
@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  constructor(private Router:Router,private BackendService:BackendService,private Utility:Utility) {
   if(window.location.pathname == "/landing/register"){
      if(Utility.getSession() != false && this.BackendService.editMode != true){
        Utility.deleteSession();
        this.BackendService.editMode = false;
        this.Router.navigateByUrl("/landing/login");
      }
   }
   }

  ngOnInit() {
  }

}
