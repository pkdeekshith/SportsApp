import { Component, OnInit } from '@angular/core';
import { BackendService} from '../shared/service/backend.service';
import { Router } from '@angular/router';
import {Utility} from '../shared/utility/utility';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(private BackendService:BackendService,
              private Utility : Utility,
              private Router : Router
              ) { 
                if(this.BackendService.memberId == undefined && this.BackendService.memberRole != 'newuser'){
                  
                  this.Router.navigateByUrl("/landing/login")
                  //let s = this.Utility.getSession();
                  //s == false ? (this.Router.navigateByUrl("/landing/login")) : (this.BackendService.memberId=s) ;
                }
              }

  ngOnInit() {
   
  }

}
