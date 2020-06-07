import { Component, OnInit } from '@angular/core';
import { BackendService} from '../shared/service/backend.service';
import { Router } from '@angular/router';
import {Utility} from '../shared/utility/utility';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private BackendService:BackendService,
    private Utility : Utility,
    private Router : Router) { 
      if(this.BackendService.memberId == undefined){
        let s = this.Utility.getSession();
        if(s){
          this.BackendService.memberId=s;
          this.BackendService.memberRole="admin";
        }else{
          this.Router.navigateByUrl("/landing/login")
        }
       
      }
    }

  ngOnInit() {
  }

}
