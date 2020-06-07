import { Component, OnInit } from '@angular/core';
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
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }
  get f() { return this.loginForm.controls; }
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
        if(data && data.memberId){
          this.BackEnd.memberId = data.memberId.toString();

          this.Utility.saveSession(data.memberId);
          if(data.roles[0].roleName == "ROLE_ADMIN"){
            this.BackEnd.memberRole="admin";
            this.Router.navigateByUrl("/admin");
          }else{
            this.Router.navigateByUrl("/user");
          }
          //If admin, then redirect to /admin
        }
        else{
          Swal.fire({
            type : 'error',
            html: '<b>Login failed</b>',
            allowOutsideClick :false,
            allowEnterKey:false,
            allowEscapeKey:false
          });
        }
      },
      error  => {
        Swal.fire({
          type : 'error',
          html: '<b>'+error.error+'</b>',
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
