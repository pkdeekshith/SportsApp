import { Component, OnInit,ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BackendService} from '../../shared/service/backend.service';
import {Config} from '../../shared/constant/config';
import {Utility} from '../../shared/utility/utility';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private Router: Router,
              private BackEnd : BackendService,
              private Config: Config,
              private Utility: Utility,
              private ngxService: NgxUiLoaderService) { }
    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    newPass="";
    flag=false;
  ngOnInit() {
    
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }
  handlePasswordErrorSpan(){
    const e1 = document.querySelector('#newPass') as HTMLInputElement;
    const e2 = document.querySelector('#confPass') as HTMLInputElement;
    e1.addEventListener('change', (event) => {
      let e = document.querySelector("#pass-error") as HTMLInputElement;
      e.style.display = "block";
    });
    e2.addEventListener('change', (event) => {
      let e = document.querySelector("#pass-error") as HTMLInputElement;
      e.style.display = "block";
    });
  } 
 
  forgotPassword(param){
   
    //this.handlePasswordErrorSpan();
    let html;
    if(param == "reset"){
       html =  "<div class='form-group'>"+
                      "<label>Enter new password</label>"+
                      "<input type='password' class='form-control col-md-9 newpass' id='newPass' />"+
                      "<label>Confirm password</label>"+
                      "<input type='password' class='form-control col-md-9 confpass' id='confPass' />"+
                      "<p class='pass-error' id='pass-error' style='display:none'>Password does not match!</p>"+
                  "</div>";
    }else if(param == "forgot"){
      html = "<div class='form-group'>"+
                      "<label>Enter Email Id</label>"+
                      "<input type='email' class='form-control col-md-9 emailpass' id='emailPass'/>"+
                     
                  "</div>";
    }
    
    Swal.fire({
      title:"Change Password",
      html: html,
      allowOutsideClick :false,
      allowEnterKey:false,
      allowEscapeKey:false,
      showCancelButton: true,
      confirmButtonText: 'PROCEED',
      cancelButtonText: 'CANCEL',
      
      preConfirm: () => {
        this.flag = false;
        if(param == "reset"){
          let a = document.querySelector("#newPass") as HTMLInputElement;
          let b = document.querySelector("#confPass") as HTMLInputElement;
          if(a.value && (a.value != b.value)){
            let e = document.querySelector("#pass-error") as HTMLInputElement;
            e.style.display = "block";
            return false;
          }else{
            let e = document.querySelector("#pass-error") as HTMLInputElement;
            e.style.display = "none";
          }
        }
      },
      }).then((result) => {
      if (result.value) {
        let req; ;
          if(param == "forgot"){
            let val = document.querySelector("#emailPass") as HTMLInputElement;
            let v= val.value;
             req = {
              "email":  v
              };
              if(v){
                this.ngxService.start();
                this.BackEnd.forgotPassword(req).subscribe(
                  data=>{
                    this.ngxService.stop();
                    if(data && data.status == "Success"){
                      Swal.fire({
                        type : 'success',
                        text: "Password sent to the email id",
                        allowOutsideClick :false
                      })
                    }else{
                      Swal.fire({
                        type : 'error',
                        text: "Failed to send the password",
                        allowOutsideClick :false
                      })
                    }
                    
                  },error=>{
                    this.ngxService.stop();
                    console.log(error);
                    
                  }
                )
              }
          }else if(param == "reset"){
            let a = document.querySelector("#newPass") as HTMLInputElement;
            let b = document.querySelector("#confPass") as HTMLInputElement;
            if(a.value && b.value){
              
                let req ={
                  "memberId":this.BackEnd.memberId,
                  "password": a.value
                  };
                  this.ngxService.start();
                  this.BackEnd.resetPassword(req).subscribe(
                    data=>{
                      this.ngxService.stop();
                      if(data && data.status == "Success"){
                        Swal.fire({
                          type : 'success',
                          text: "Password changed successfully",
                          allowOutsideClick :false
                        })
                      }else{
                        Swal.fire({
                          type : 'error',
                          text: "Password reset Failed",
                          allowOutsideClick :false
                        })
                      }
                      
                    },error=>{
                      this.ngxService.stop();
                      console.log(error);
                      
                    }
                  )
              
             
            }
          }
      }else{

      }
    })
  }
  get f() { return this.loginForm.controls; }
  passwordChange =()=>{
    alert(3);
  }
  onSubmit(){
    this.submitted = true;
    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }
    this.ngxService.start();
    let req={"email":this.f.username.value,"password":this.f.password.value};
    this.BackEnd.login(req)
    .subscribe(
      
      data  => {
        this.ngxService.stop();
        if(data && data.body && data.body.memberId){
          let token = data.headers.get("auth-token");
          if(!token) token = "test-token";
          localStorage.setItem("auth-token",token);
          data = data.body;
          this.BackEnd.memberId = data.memberId.toString();
          this.BackEnd.cardAmount=0; //Just reset the card amount,if persist somehow.
          this.BackEnd.facilityToValidate=undefined;
          this.BackEnd.exploredFacility=undefined;
          this.BackEnd.preRegData = undefined;
          this.BackEnd.searchCenter = undefined;
          this.Utility.saveSession(data.memberId);
          //data.firstLogin = true;
          if(data.firstLogin){
            //first login-password need to be reset
            this.forgotPassword('reset');
          }else{
            if(data.roles[0].roleName == "ROLE_ADMIN"){
              this.BackEnd.memberRole="admin";
              this.Router.navigateByUrl("/admin");
            }else{
              this.BackEnd.memberRole="user";
              this.Router.navigateByUrl("/user");
            }
          }
          
        }
        else{
          Swal.fire({
            type : 'error',
            html: '<b>Incorrect Username or Password!</b>',
            allowOutsideClick :false,
            allowEnterKey:false,
            allowEscapeKey:false
          });
        }
      },
      error  => {
        Swal.fire({
          type : 'error',
          html: '<b>'+"Incorrect Username or Password!"+'</b>',
          allowOutsideClick :false,
          allowEnterKey:false,
          allowEscapeKey:false
        });
        this.ngxService.stop();
        
      }
      
      );
    //this.Router.navigateByUrl("/user");
  }

}
