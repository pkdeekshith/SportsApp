import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
import {MessageService} from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-facility',
  templateUrl: './facility.component.html',
  styleUrls: ['./facility.component.css']
})
export class FacilityComponent implements OnInit {
  center="";
  centreName;
  facilityList=[];
  feature=[];

  constructor(private BackEnd : BackendService
    ,private messageService:MessageService,
    private ngxService: NgxUiLoaderService,private Router: Router,) { 
      this.center = "Jimmy George Sports Hub";
    }

  ngOnInit() {
    this.centreName = "Jimmy George";
    this.feature = ["indoor","Hard Surface"]
      this.ngxService.start();
      //check if coming from home page search
      let req="0";
      if(this.BackEnd.searchCenter && this.BackEnd.searchCenter.centreId){
        req = this.BackEnd.searchCenter.centreId.toString();
        this.BackEnd.searchCenter=undefined
      }

      this.BackEnd.getAllFacilities(req)
      .subscribe(
        data  => {
        
          if(data){
            this.ngxService.stop();
            this.facilityList = data;
          }
        },
        error  => {
          this.ngxService.stop();
          console.log("Error", error);
        }
        
        );
  }
  goToValidatePage=(item)=>{
    this.BackEnd.facilityToValidate = item;
    this.Router.navigateByUrl("/landing/validate");
  }

}
