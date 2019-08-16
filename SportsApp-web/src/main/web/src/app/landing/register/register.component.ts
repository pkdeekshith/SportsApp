import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import {Message} from 'primeng//api';
import {MessageService} from 'primeng/api';
import {Data} from '../../shared/data/data';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(private formBuilder: FormBuilder,private BackEnd : BackendService,private ngxService: NgxUiLoaderService,
    private messageService: MessageService, private Data:Data,private Router : Router) {}
  preferredSports =[];
  failities=[];
  slotobject : any;
  display : boolean = false;
  displayReview : boolean = false;
  showProceedBtn :boolean = false;
  selectedFacility :any = {
    'facility':"",
    "subFacility":"",
    "timeTable":""
  } ;
  registerForm: FormGroup;
  submitted:any=false;
  public imagePath;
  imgURL: any;
  public message: string;
  stateList:any=[];
  districtList :any=[];
  preview(files) {
    if (files.length === 0)
      return;
 
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    } 
    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]); 
    reader.onload = (_event) => { 
      this.imgURL = reader.result; 
    }
  }
  ngOnInit() {
   
    this.registerForm = this.formBuilder.group({
      memberName: ['', Validators.required],
      gender: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      guardName : ['', Validators.required],
      address : ['', Validators.required],
      city : ['', Validators.required],
      district : ['', Validators.required],
      country : ['INDIA', Validators.required],
      state : ['', Validators.required],
      mobNumber : ['', Validators.required],
      pin : ['', Validators.required],
      email : ['', Validators.required],
      idNumber : ['', Validators.required],
      idType : ['', Validators.required],
      student : ['', Validators.required],
      govt : ['', Validators.required],
      coaching : ['', Validators.required],
      prefSport1 : ['', Validators.required],
      facility1: ['', Validators.required],
      center : ['Jimmy George', Validators.required],
      memCost : ['', Validators.required],
      memID :[],
      memStartDate:[new Date(),Validators.required],
      memEndDate:[new Date(new Date().setFullYear(new Date().getFullYear() + 1)),Validators.required],
      isChecked : ['']

  });
  this.stateList = Object.keys(this.Data.stateMap);
  //get preferred sport
  this.ngxService.start();
  this.BackEnd.getPreferredSports()
  .subscribe(
       data  => {
         this.preferredSports.length=0;
         let temp=[];
         data.forEach(function(i){ 
         temp.push({"label":i.facilityTypeName,"value":i.facilityTypeId})});
         this.preferredSports=temp; 
         this.BackEnd.getMemberShiptype().subscribe(
           data=>{
              this.f.memCost.patchValue(data[0].memberShipCost);
              this.f.memID.patchValue(data[0].memberShipId);
              this.ngxService.stop();
           },error =>{
              alert("Server error!")
           }
         ) 
       },
       error  => {
         alert("Server error occured")
         console.log("Error", error);
       }
      
       );
  }
  get f() { return this.registerForm.controls; }
  onSubmit(){
    this.submitted = true;
  }
  handleStateSelection(){
    this.districtList = this.Data.stateMap[this.f.state.value];
  }
  proceedAfterSportSelection(){
    //call availablility check
    let errorMsgs=[];
    let proceedFlag = false;
    let severity;
    if(this.f.facility1.value=="" || this.f.prefSport1.value==""){
      this.messageService.clear()
      this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please select facility'});
      return;
    }
    this.ngxService.start();
    this.BackEnd.verifyAvailability(this.f.facility1.value)
    .subscribe(
      data  => {
        this.ngxService.stop();
        //data={"facility":[{"slotAavailable":"Y","facilityId":"10","validation":"second mesage"},{"slotAavailable":"Y","facilityId":"8","validation":"Booking window for the facility - Aerobics / Yoga was between Mon Jul 01 00:00:00 UTC 2019 and Fri Jul 05 23:59:59 UTC 2019!!!"}],"status":"Success"} ;
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
          //this.messageService.clear();
          //this.messageService.add({severity:severity, summary: '', detail:errorMsgs.join("<br/>")});
        }else{
          this.showProceedBtn = true;
          if(errorMsgs.length){
            severity = "info";
            
            Swal.fire({
              type : 'info',
              html: '<b>'+errorMsgs.join("<br/>")+'</b>',
              allowOutsideClick :false,
              allowEnterKey:false,
              allowEscapeKey:false
            }).then((result) => {
              if (result.value) {
                document.querySelector("#fade-in").classList.add("show");
              }
          })
            //this.messageService.clear();
            //this.messageService.add({severity:severity, summary: '', detail:errorMsgs.join("<br/>")});
          }else{
            severity = "success";
            Swal.fire({
              type : 'success',
              html: '<b>Please provide personal details</b>',
              allowOutsideClick :false,
              allowEnterKey:false,
              allowEscapeKey:false
            }).then((result) => {
              if (result.value) {
                document.querySelector("#fade-in").classList.add("show");
              }
          })
            
            //this.messageService.clear();
            //this.messageService.add({severity:severity, summary: "Please provide personal details.", detail:''});
          }
        }

      },
      error  => {
       
    });
   
    
   
   
  }
  handlePreferredSportSelection(){
    this.f.facility1.patchValue("");
    this.ngxService.start();
    this.BackEnd.getFacilityBasedOnPreferredSprtsSelected(this.f.prefSport1.value)
    .subscribe(
      data  => {
        this.ngxService.stop();
        this.failities.length=0;
        let temp=[];
        data.forEach(function(i){ 
          temp.push({"label":i.facilityName,"value":i.facilityId})});
        this.failities=temp;  
      },
      error  => {
        console.log("Error", error);
      }
     
    );
  }
  proceedToSlotSelection(){
     
    this.submitted = true;
    this.f.isChecked.patchValue(false);
    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }
    if(this.imgURL == undefined){
      this.messageService.clear();
      this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please upload your photo'});
      return;
    }
     
    this.ngxService.start();
    this.BackEnd.getSlotsAvailableToBook(this.f.facility1.value).subscribe(
      data =>{
        this.ngxService.stop();
        this.display = true;
        this.slotobject = data;
      },error =>{

      }
    )
    
  }
  checkValue(fac,subfac,time){
    if(this.f.isChecked.value){
      document.querySelectorAll(".slotCheck").forEach(function(i){ i.classList.add("disableClick") });
      let eveTar = event.target as HTMLInputElement;
      eveTar.classList.remove("disableClick");
      this.selectedFacility.facility=fac;
      this.selectedFacility.subFacility=subfac;
      this.selectedFacility.timeTable=time;
    }else{
      document.querySelectorAll(".slotCheck").forEach(function(i){ i.classList.remove("disableClick") });
    }
 }
 proceedToReviewDetails(){
   if(Object.keys(this.selectedFacility.facility).length == 0){
     this.messageService.clear();
     this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please select a slot'});
   }else{
    this.display = false;
    this.displayReview = true;
   }
   
 }
 registerMember(){
    this.ngxService.start();
    this.BackEnd.saveMember(this.f,this.imgURL).subscribe(
      data =>{
        //Call saveBooking
        if(data && data.status == "Success"){
          this.BackEnd.saveBooking(this.selectedFacility,data.memberId).subscribe(
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
                  this.Router.navigateByUrl("/landing/login");
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
 slot(t:any){
    let tail="AM";
    let hour:any= t/60;
    if(hour>12){hour = hour-12; tail="PM";}
    let minute:any = t%60;
    //if(minute ==0) { minute ="00";}
    return parseInt(hour) +":"+(parseInt(minute)==0?"00":parseInt(minute))+tail;
 }
  
}
