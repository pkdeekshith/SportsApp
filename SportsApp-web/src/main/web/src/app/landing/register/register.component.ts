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
  constructor(private formBuilder: FormBuilder,public BackEnd : BackendService,private ngxService: NgxUiLoaderService,
    private messageService: MessageService, private Data:Data,private Router : Router) {
      if(this.BackEnd.editMode == true){
        this.editMode = true;
       // document.querySelector("#fade-in").classList.add("show");
        debugger;
 
        //this.f.patchValue("memberName":"khkjh");
        
      }
    }
  editMode:boolean = false;  
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
      memberName: ['', [Validators.required,Validators.pattern('^[A-Za-z .]+$')]],
      gender: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      guardName : ['', [Validators.required,Validators.pattern('^[A-Za-z .]+$')]],
      address : ['', Validators.required],
      city : ['', Validators.required],
      district : ['', Validators.required],
      country : ['INDIA', Validators.required],
      state : ['', Validators.required],
      mobNumber : ['', [Validators.required,Validators.minLength(10),Validators.maxLength(11),Validators.pattern('^[0-9]+$')]],
      pin : ['', [Validators.required,Validators.minLength(6),Validators.pattern('^[0-9]+$')]],
      email : ['', [Validators.required,Validators.email]],
      idNumber : ['', Validators.required],
      idType : ['', Validators.required],
      student : ['', Validators.required],
      govt : ['', Validators.required],
      coaching : ['', Validators.required],
      center : ['', Validators.required],
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
              //this.f.center.patchValue(this.BackEnd.facilityToValidate.centerName);
              if(this.BackEnd.facilityToValidate){
                this.f.center.patchValue(this.BackEnd.facilityToValidate.centerName);
              }
              else if(this.BackEnd.memberData){
                this.f.center.patchValue(this.BackEnd.memberData.centerName);
              }
              this.BackEnd.cardAmount= data[0].memberIdCardAmount;
              
              //edit mode on
              if(this.editMode){
                document.querySelector("#fade-in").classList.add("show");
                let {memberName,gender,dob,fatherName,street,country,
                  state,pincode,district,city,memberContactNo,email,
                  memberIdProof,memberIdProofType,isCoaching,isGovt,isStudent,memberPhoto,facilityType} = this.BackEnd.memberData;
                  this.setDistrictOptions(state);
                  this.registerForm.patchValue({
                  "memberName": memberName,
                  "gender": gender,
                  "dateOfBirth": new Date(dob),
                  "guardName" : fatherName,
                  "address" : street,
                  "city" : city,
                  "district" :district,
                  "country" : country,
                  "state" : state,
                  "mobNumber" : memberContactNo,
                  "pin" : pincode,
                  "email" : email,
                  "idNumber" : memberIdProof,
                  "idType" : memberIdProofType,
                  "student" : isStudent == true ? "Yes" : "No",
                  "govt" : isGovt == true ? "Yes" : "No",
                  "coaching" : isCoaching == true ? "Yes" :"No",
                  "prefSport1" : this.setPreferredSport(facilityType)
                });
                this.imgURL = memberPhoto;
              }
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
  setDistrictOptions(state){
    this.districtList = this.Data.stateMap[state];
  }
  setPreferredSport(facility){
    let e=[];
    facility.forEach(function(i){e.push(i.facilityTypeId)});
    return e;
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
    // this.ngxService.start();
    // this.BackEnd.getFacilityBasedOnPreferredSprtsSelected(this.f.prefSport1.value)
    // .subscribe(
    //   data  => {
    //     this.ngxService.stop();
    //     //cover form if already opened
    //     this.showProceedBtn = false;
    //     document.querySelector("#fade-in").classList.remove("show");
    //     this.failities.length=0;
    //     let temp=[];
    //     data.forEach(function(i){ 
    //       temp.push({"label":i.facilityName,"value":i.facilityId})});
    //     this.failities=temp;  
    //   },
    //   error  => {
    //     console.log("Error", error);
    //   }
     
    // );
  }
  handlePreferredFacilitySelection(){
    //cover form if already opened
    this.showProceedBtn = false;
    document.querySelector("#fade-in").classList.remove("show");
  }
  proceedToSlotSelection(){
     
    this.submitted = true;
    this.f.isChecked.patchValue(false);
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      try{
        let e= document.querySelectorAll(".form-block input.ng-invalid,.form-block select.ng-invalid") as NodeList;
        if(e && e.length){
          let r= e[0] as HTMLInputElement;
          r.focus();
        }
      }catch(er){
        console.log(er);
        
      }
      return;
    }
    if(this.imgURL == undefined){
      this.messageService.clear();
      this.messageService.add({key: 'errorToast', severity:'error', summary: '', detail: 'Please upload your photo'});
      return;
    }
    this.validateUserName();
  }
  validateUserName(){
    let req={
      "email": this.f.email.value,
      "ignore": this.BackEnd.editMode? true : false
      }
    this.ngxService.start();
    this.BackEnd.validateUserName(req).subscribe(
      data=>{
        this.ngxService.stop();
        if(data.isValid){
          this.BackEnd.memberRole = "newuser";
          this.BackEnd.preRegData.form = this.f;
          this.BackEnd.preRegData.imgURL = this.imgURL;
          this.Router.navigateByUrl("/user/book");
        }else{
          Swal.fire({
            type : 'error',
            html: '<b>'+data.message+'</b>',
            allowOutsideClick :false
          });
        }
      },error=>{
        this.ngxService.stop();
        alert("Network Error");
      }
    );

    
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
    this.BackEnd.saveMember(this.f,this.imgURL,"").subscribe(
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
 goBackToProfile(){
  this.BackEnd.editMode = false; 
  this.Router.navigateByUrl("/user/profile");
 }
 editMember(){
   this.ngxService.start();
   this.BackEnd.saveMember(this.f,this.imgURL,this.BackEnd.memberId).subscribe(
     data =>{
      this.ngxService.stop();
       if(data && data.status == "Success"){
        Swal.fire({
          type : 'success',
          html: '<b>Details has been modified successfully</b>',
          allowOutsideClick :false,
          allowEnterKey:false,
          allowEscapeKey:false
        }).then((result) => {
          if (result.value) {
          //this.Router.navigateByUrl("/user/profile");
          this.goBackToProfile();
          }
      })
       }else{
        Swal.fire({
          type : 'error',
          html: '<b>Server Error</b>',
          allowOutsideClick :false,
          allowEnterKey:false,
          allowEscapeKey:false
       }).then((result) => {
        if (result.value) {
        //this.Router.navigateByUrl("/user/profile");
        this.goBackToProfile();
        }
      })
      }
       
     },error=>{
       this.ngxService.stop();
     }
   )
 }
  
}
