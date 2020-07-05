import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
import {MessageService} from 'primeng/api';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sport',
  templateUrl: './sport.component.html',
  styleUrls: ['./sport.component.css']
})
export class SportComponent implements OnInit {

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
      this.BackEnd.getAllFacilities("0")
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
  goToBook(item){
    this.BackEnd.exploredFacility = item;
    this.ngxService.start();
    this.BackEnd.verifyAvailability([item.facilityId])
    .subscribe(
      data=>{

        this.ngxService.stop();
        if(data && data.status == "Success" && data.facility[0].slotAavailable == "Y"){
          this.Router.navigateByUrl("/user/book");
        }else{
          Swal.fire({
            type : 'error',
            html: '<b>'+"No Available Slots!"+'</b>',
            allowOutsideClick :false,
            allowEnterKey:false,
            allowEscapeKey:false
          }).then((result) => {
            if (result.value) {
           
            }
          })
        }
      },error=>{
        this.ngxService.stop();
        Swal.fire({
          type : 'error',
          html: '<b>'+"No Available Slots!"+'</b>',
          allowOutsideClick :false,
          allowEnterKey:false,
          allowEscapeKey:false
        }).then((result) => {
          if (result.value) {
         
          }
        })
      });
    
  }

}
