import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  WID=0;
  centerList=[];
  facilityList=[];
  subFacilityList=[];
  FacilitiesToDisable=[];
  FacilitiesToEnable=[];
  FtoEnable=[];
  FtoDisable=[];
  center2;
  center3;
  facilityId3;
  SubFtoDisable=[];
  SubFtoEnable=[];
  SubFacilitiesToEnable=[];
  SubFacilitiesToDisable=[];
  centerToEnable=[];
  centerToDisable=[];
  CtoEnable=[];
  CtoDisable=[];

  windowStart;
  windowEnd;
  windowFacility;
  fullWindowData;
  windowcenter;
  constructor(private BackendService:BackendService,
    private NgxUiLoaderService:NgxUiLoaderService,private MessageService:MessageService) { }

  ngOnInit() {
    this.centerList=[];
   // this.centerList.push({label:"Jimmy George",value:2});
   this.NgxUiLoaderService.start();
   this.BackendService.getCenterListAdmin().subscribe(
     data=>{
       this.NgxUiLoaderService.stop();
       //this.centerList = data;
       data.forEach(i => { 
        if(i.onlineActive){
          this.centerList.push(i);
          this.centerToDisable.push({label:i.centreName,value:i});
        }else{
          this.centerToEnable.push({label:i.centreName,value:i});
        }
      });
     },error=>{
       this.NgxUiLoaderService.stop();
       console.log(error);
       
     }
   )
  }

  resetCenterSettingsPage(){
    this.centerList=[];
    this.centerToDisable=[];
    this.centerToEnable=[];
   // this.centerList.push({label:"Jimmy George",value:2});
   this.NgxUiLoaderService.start();
   this.BackendService.getCenterListAdmin().subscribe(
     data=>{
       this.NgxUiLoaderService.stop();
      // this.centerList = data;
       data.forEach(i => { 
        if(i.onlineActive){
          this.centerList.push(i);
          this.centerToDisable.push({label:i.centreName,value:i});
        }else{
          this.centerToEnable.push({label:i.centreName,value:i});
        }
      });
     },error=>{
       this.NgxUiLoaderService.stop();
       console.log(error);
       
     }
   )
  }
  setScreen(ID){
    this.WID = ID;
  }
  handleCenterSelection(id,wid){
    this.FacilitiesToEnable=[];
    this.FacilitiesToDisable=[];
    this.BackendService.getFacilitiesForAdmin(id).subscribe(
      data=>{
        if(data){
          this.facilityList=data;
          //set FacilitiesToDisable,FacilitiesToEnable
          data.forEach(i => { 
            if(i.onlineActive){
              this.FacilitiesToDisable.push({label:i.facilityName,value:i});
            }else{
              this.FacilitiesToEnable.push({label:i.facilityName,value:i});
            }
          });
        }
      },
      error=>{

      }
    )
  }
  handleFacilitySelection(facId,winID){
    this.SubFacilitiesToDisable=[];
    this.SubFacilitiesToEnable=[];
    this.SubFtoDisable=[];
    this.SubFtoEnable=[];
    this.BackendService.getAllSubFacilitiesAdmin(facId).subscribe(
      data=>{
        if(data && data.length && data[0].subFacility){
          //set SubFacilitiesToEnable,SubFacilitiesToDisable
          data[0].subFacility.forEach(i => { 
            if(i.onlineActive){
              this.SubFacilitiesToDisable.push({label:i.subFacilityName,value:i});
            }else{
              this.SubFacilitiesToEnable.push({label:i.subFacilityName,value:i});
            }
          });
        }
      },error=>{

      }
    )
  }
  resetFacilitySettingsPage(){
          this.FtoDisable=[];
          this.FtoEnable=[];
          this.center2="";
          this.FacilitiesToEnable=[];
          this.FacilitiesToDisable=[];
  }
  resetSubFacilitySettingsPage(){
    this.center3="";
    this.facilityList=[];
    this.facilityId3="";
    this.SubFtoDisable=[];
    this.SubFtoEnable=[];
    this.SubFacilitiesToDisable=[];
    this.SubFacilitiesToEnable=[];
  }
  enableFacilities(){
    if(!this.FtoEnable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.FtoEnable.length;i++){
      this.FtoEnable[i].onlineActive = true;
    }
    this.BackendService.updateFacilities(this.FtoEnable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetFacilitySettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  
  disableFacilities(){
    if(!this.FtoDisable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.FtoDisable.length;i++){
      this.FtoDisable[i].onlineActive = false;
    }
    this.BackendService.updateFacilities(this.FtoDisable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetFacilitySettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  enableSubFacilities(){
    if(!this.SubFtoEnable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.SubFtoEnable.length;i++){
      this.SubFtoEnable[i].onlineActive = true;
    }
    this.BackendService.updateSubFacilitiesAdmin(this.SubFtoEnable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetSubFacilitySettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  disableSubFacilities(){
    if(!this.SubFtoDisable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.SubFtoDisable.length;i++){
      this.SubFtoDisable[i].onlineActive = false;
    }
    this.BackendService.updateSubFacilitiesAdmin(this.SubFtoDisable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetSubFacilitySettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  disableCenters(){
    if(!this.CtoDisable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.CtoDisable.length;i++){
      this.CtoDisable[i].onlineActive = false;
    }
    this.BackendService.updateCenters(this.CtoDisable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetCenterSettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  enableCenters(){
    if(!this.CtoEnable.length) return;
    this.NgxUiLoaderService.start();
    for(let i=0;i<this.CtoEnable.length;i++){
      this.CtoEnable[i].onlineActive = true;
    }
    this.BackendService.updateCenters(this.CtoEnable).subscribe(
      data=>{
        if(data && data.status=="Success"){
          this.resetCenterSettingsPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }else{

        }
        
        this.NgxUiLoaderService.stop();
      },
      error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  getCurrentBookingWindow(id){
    this.NgxUiLoaderService.start();
    this.BackendService.getOnlineBookingWindow().subscribe(
      data=>{
        this.NgxUiLoaderService.stop();
        if(data){
          this.fullWindowData = data;
          data.forEach((item)=>{
            if(item.facilityId == id){              
              this.windowStart = item.bookingStartDate;
              this.windowEnd = item.bookingEndDate;
              return;
            }
          })
        }
      },error=>{
        this.NgxUiLoaderService.stop();
      }
    )
  }
  updateBookingWindow(){
    if(this.windowcenter && this.windowFacility && this.windowStart && this.windowEnd){
      let temp = this.fullWindowData;
      temp.forEach((item)=>{
      if(item.facilityId == this.windowFacility){
        item.bookingStartDate = this.windowStart;
        item.bookingEndDate=this.windowEnd;
        return;
      }
    })
    console.log(temp);
    this.NgxUiLoaderService.start();
    this.BackendService.saveBookingWindow(temp).subscribe(
      data=>{
        this.NgxUiLoaderService.stop();
        if(data && data.status=="Success"){
          this.resetBookingWindowPage();
          this.MessageService.clear();
          this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});    
        }
      },error=>{
        this.NgxUiLoaderService.stop();
      }
    )
    }
 
  }
  resetBookingWindowPage(){
    this.windowcenter="";
    this.windowFacility="";
    this.windowStart="";
    this.windowEnd="";

  }
}
