import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import {MessageService} from 'primeng/api';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-pre-register',
  templateUrl: './pre-register.component.html',
  styleUrls: ['./pre-register.component.css']
})
export class PreRegisterComponent implements OnInit {
  preferredSports =[];
  failities=[];
  prefSport;
  prefFacility;
  temp={};
  constructor(private Router:Router,private BackEnd : BackendService,private ngxService: NgxUiLoaderService,private messageService: MessageService) { }

  ngOnInit() {
    this.ngxService.start();
    let center;
    if( this.BackEnd.facilityToValidate && this.BackEnd.facilityToValidate.centreId){
      center = this.BackEnd.facilityToValidate.centreId.toString();
    }
   
    this.BackEnd.getPreferedSportAndFacilities(center).subscribe(
      data=>{
        this.temp={};
        console.log(data);
        data.forEach((i)=> { 
          let a = [];
          i.facilities.forEach((j)=> { a.push(j.facilityId)});
          this.temp[i.facilityTypeId] = a;
          });
          this.BackEnd.getPreferredSports()
          .subscribe(
               data  => {
                this.ngxService.stop();
                 this.preferredSports.length=0;
                 let temp=[];
                 data.forEach(function(i){ 
                 temp.push({"label":i.facilityTypeName,"value":i.facilityTypeId})});
                 this.preferredSports=temp; 
                 //Now, find the pref sport user selected using this.temp map
                 let f;
                  if(this.BackEnd.facilityToValidate){
                     Object.keys(this.temp).forEach((key)=>{
                      let i=this.temp[key];
                      if(i.indexOf(this.BackEnd.facilityToValidate.facilityId)>-1){
                        f=key; return;
                       }
                    });
                    if(f){
                      this.prefSport = [Number(f)];
                      this.handlePreferredSportSelection();
                    }
                    
                  }
               },error=>{
                 this.ngxService.stop();
               })
      },error=>{
        this.ngxService.stop();
      }
    );

    
  }

  handlePreferredSportSelection(){
    this.ngxService.start();
    let centerid="0";
    if( this.BackEnd.facilityToValidate && this.BackEnd.facilityToValidate.centreId){
      centerid = this.BackEnd.facilityToValidate.centreId;
    }
    this.BackEnd.getFacilityBasedOnPreferredSprtsSelected(centerid,this.prefSport)
    .subscribe(
      data  => {
        this.ngxService.stop();
      
        this.failities.length=0;
        let temp=[];
        data.forEach(function(i){ 
          temp.push({"label":i.facilityName,"value":i.facilityId})});
          this.failities=temp; 
          //Set default facility wiht user selected one 
          if(this.BackEnd.facilityToValidate){
            this.prefFacility = [Number(this.BackEnd.facilityToValidate.facilityId)];
          } 
      },
      error  => {
        console.log("Error", error);
      }
     
    );
  }
  proceedAfterSportSelection(){
    //call availablility check
    let errorMsgs=[];
    let proceedFlag = false;
    let severity;
    if(!this.prefSport || !this.prefFacility){
      this.messageService.clear()
      this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please select facility'});
      return;
    }
    this.ngxService.start();
    this.BackEnd.verifyAvailability(this.prefFacility)
    .subscribe(
      data  => {
        this.ngxService.stop();
      //  data={"facility":[{"slotAavailable":"N","facilityId":"10","validation":"second mesage"},{"slotAavailable":"N","facilityId":"8","validation":"Booking window for the facility - Aerobics / Yoga was between Mon Jul 01 00:00:00 UTC 2019 and Fri Jul 05 23:59:59 UTC 2019!!!"}],"status":"Success"} ;
        data.facility.forEach(function(i) 
          { 
            if(i.slotAavailable=="N") { 
              errorMsgs.push(i.validation);
            }else{
              proceedFlag = true;
            }
        });
        if(!proceedFlag){
          severity = "error";
          Swal.fire({
            type : 'error',
            html: '<b>'+errorMsgs.join("<br/>")+'</b>',
            allowOutsideClick :false,
            allowEnterKey:false,
            allowEscapeKey:false
          }).then((result) => {
            if (result.value) {
           
            }
        })
          
        }else{
         //proceed to next page
          this.BackEnd.preRegData = {
            "facility":this.prefFacility,
            "prefsport": this.prefSport
          }
          console.log("proceed to next page");
          this.Router.navigateByUrl("/landing/register");
          
        }

      },
      error  => {
        this.ngxService.stop();
        alert("Server Error");
    });
   
    
   
   
  }

}
