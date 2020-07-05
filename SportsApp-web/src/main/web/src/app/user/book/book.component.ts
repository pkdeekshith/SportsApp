import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Config } from "../../shared/constant/config";
 

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  slotobject=[{'facilityPhoto':'','facilityName':'','centerName':''}];
  slotSelected:boolean=false;
  memCost=0;
  cardCost=0;
  showSplit=false;
  selectedFacility :any = {
    'facility':"dfg",
    "subFacility":"dfgdfg",
    "timeTable":"fgdg"
  } ;
  constructor(private BackEnd : BackendService,private ngxService: NgxUiLoaderService,
    private Router: Router,private Config: Config) { 

  }

  slot(t:any){
    let tail="AM";
    let hour:any= t/60;
    if(hour>12){hour = hour-12; tail="PM";}
    let minute:any = t%60;
    //if(minute ==0) { minute ="00";}
    return parseInt(hour) +":"+(parseInt(minute)==0?"00":parseInt(minute))+tail;
 }
 checkValue(fac,subfac,time){
    if(document.querySelector(".slotcheck.active")){
      document.querySelector(".slotcheck.active").classList.remove("active");
    }
    
    this.slotSelected = true;
    let eveTar = event.target as HTMLInputElement;
    eveTar.classList.add("active");
    this.selectedFacility.facility=fac;
    this.selectedFacility.subFacility=subfac;
    this.selectedFacility.timeTable=time;
  
}
doBooking(){
  let paymentRequest= {
    "memberId":"",
    "accountId":[],
    "amount":this.memCost+this.selectedFacility.subFacility.rateMonthly+this.cardCost,
    "isNewMember":"N"
    };
  this.ngxService.start();
  if(this.BackEnd.memberRole == "user"){
    paymentRequest.memberId = this.BackEnd.memberId; //login user
    this.BackEnd.saveBooking(this.selectedFacility,this.BackEnd.memberId).subscribe(
      data=>{
        paymentRequest.accountId.push(data.accountId);
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
            //this.Router.navigateByUrl("/landing/login");
            this.ngxService.start();
            this.BackEnd.initiatePayment(paymentRequest).subscribe(
              data=>{
                this.ngxService.stop();
               // data= {"key":"3DSZ2OM25982AC29157AC29158NNFXKV","status":"S"};
                 if(data.status == "S"){
                   localStorage.setItem("orderId",data.key);
                   const url= this.Config.URL.paymentFinal+data.key;
                   window.location.href=url;
                 }else{
                   console.log("in initiate oayment error");
                   
                 }
                 
              },error=>{
                console.log("in error callback of initiate payment");
                
              }
            );
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
  }else{
    this.BackEnd.saveMember(this.BackEnd.preRegData.form,this.BackEnd.preRegData.imgURL,"").subscribe(
      data =>{
        paymentRequest.isNewMember="Y";
        paymentRequest.memberId = data.memberId.toString();
        paymentRequest.accountId.push(data.accountId);
        //Call saveBooking
        if(data && data.status == "Success"){
          this.BackEnd.saveBooking(this.selectedFacility,data.memberId).subscribe(
            data=>{
              paymentRequest.accountId.push(data.accountId);
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
                  //this.Router.navigateByUrl("/landing/login");
                  this.BackEnd.initiatePayment(paymentRequest).subscribe(
                    data=>{
                      this.ngxService.stop();

                     // data= {"key":"3DSZ2OM25982AC29157AC29158NNFXKV","status":"S"};
                       if(data.status == "S"){
                         const url= this.Config.URL.paymentFinal+data.key;
                         window.location.href=url;
                       }else{
                         console.log("in initiate oayment error"); 
                       }
                    },error=>{
                      this.ngxService.stop();
                      console.log("in error callback of initiate payment");            
                    }
                  );
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
        }else{
          this.ngxService.stop();
          alert("Server error");
        }
      },error =>{
        this.ngxService.stop();
        alert("Server error")
      }
    )
  }
  
}
ngOnInit() {
    //login user
    if(this.BackEnd.memberRole == "user")
    {
      let facililty = this.BackEnd.exploredFacility;
      this.ngxService.start();
      this.BackEnd.getSlotsAvailableToBook([facililty.facilityId]).subscribe(
      data =>{
       this.ngxService.stop();
       this.slotobject = data;
     },error =>{
      this.ngxService.stop();
      alert("Server Error");
     }
   )
      
    }//fresh user
    else{
      this.memCost = this.BackEnd.preRegData.form.memCost.value;
      this.cardCost = this.BackEnd.cardAmount;
      this.ngxService.start();
      this.BackEnd.getSlotsAvailableToBook(this.BackEnd.preRegData.facility).subscribe(
      data =>{
       this.ngxService.stop();
       this.slotobject = data;
     },error =>{
      this.ngxService.stop();
      alert("Server Error");
     }
   )
    }
    
  
  }


}
