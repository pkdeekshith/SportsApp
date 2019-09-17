import { Component, OnInit ,ElementRef} from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  availableSlots : any=[];
  centreName:String;
  centrelist : any = [];
  facilityList : any =[];
  subFacilityList :any=[];
  facility:any={};
  subFacility:any={};
  selectedSlotObj:any={};
  bookingRangeObj:any={};
  displayReview:boolean=false;
  constructor(private BackEnd : BackendService,
    private host : ElementRef,private messageService:MessageService,
    private ngxService: NgxUiLoaderService) {
      
    }
    ngOnInit() {
      this.centreName = "Jimmy George";
      this.ngxService.start();
      this.BackEnd.getAllFacilities()
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
      getSlots(){
        if(this.subFacilityList.length == 0 || Object.keys(this.subFacility).length==0){
          this.messageService.clear();
          this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please select facility details'});    
         return;
        }
        this.ngxService.start();
        this.BackEnd.getTimeSlotsForSubFacility(this.subFacility.subFacilityId).subscribe(
          data =>{
            if(data){
              this.ngxService.stop();
              this.bookingRangeObj = data[0];
              this.availableSlots = data[0].timetable;
            }
          },error=>{
            this.ngxService.stop();
          }
          )
        }
        handleFacilityChange(){
          this.availableSlots.length = 0;
          
          this.ngxService.start();
          this.BackEnd.getsubFacilitiesOfFacility(this.facility.facilityId).subscribe(
            data=>{
              this.ngxService.stop();
              if(data){
                this.subFacilityList = data[0].subFacility;
                this.subFacility={};
              }
            },error =>{
              this.ngxService.stop();
            }
            )
          }
          handleSubFacilityChange(){
            this.availableSlots.length = 0;
          }
          handleSlotClick(time){
            let eveTar = event.target as HTMLInputElement;
            if(eveTar.checked){
              document.querySelectorAll(".slotCheck").forEach(function(i){ i.classList.add("disableClick") });
              eveTar.classList.remove("disableClick");
              this.selectedSlotObj = time;
            }else{
              this.selectedSlotObj={};
              document.querySelectorAll(".slotCheck").forEach(function(i){ i.classList.remove("disableClick") });
            }
          }
          proceedToReviewDetails(){
            if(Object.keys(this.selectedSlotObj).length == 0){
              this.messageService.clear();
              this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please select a slot'});    
              return;
            }
            this.displayReview = true;
          }
          doBooking(){
            this.ngxService.start();
            let obj={
              "facility":this.facility,
              "subFacility":this.subFacility,
              "timeTable":this.selectedSlotObj
            }
            this.BackEnd.saveBooking(obj,this.BackEnd.memberId).subscribe(
              data=>{
                this.ngxService.stop();
                if(data && data.status == "Success"){
                  Swal.fire({
                    type : 'info',
                    html: '<b>You will be now redirected to payment page.Booking confirmation will be sent to the registered email and mobile number after the successfull payment.</b>',
                    allowOutsideClick :false,
                    allowEnterKey:false,
                    allowEscapeKey:false
                  }).then((result) => {
                    if (result.value) {
                      //reset page
                    this.facility={};
                    this.subFacility={};
                    this.availableSlots.length=0;
                    this.displayReview = false;  
                    window.open("https://www.google.com","_blank");
                    }
                })}else{
                  this.ngxService.stop();
                  alert("server error");
                }
              },error =>{
                this.ngxService.stop();
                alert("Server error");
              }
            )
          }
          slot(t:any){
            let tail="AM";
            let hour:any= t/60;
            if(hour>12){hour = hour-12; tail="PM";}
            let minute:any = t%60;
            //if(minute ==0) { minute ="00";}
            return parseInt(hour) +":"+(parseInt(minute)==0?"00":parseInt(minute))+tail;
          }
          
          
        }
        