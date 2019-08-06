import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BackendService} from '../../shared/service/backend.service';
import {Config} from '../../shared/constant/config';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private Router: Router,
              private BackEnd : BackendService,
              private Config: Config) { }
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
    // this.BackEnd.login({})
    // .subscribe(
    //   data  => {
    //     console.log("POST Request is successful ", data);
    //   },
    //   error  => {
    //     console.log("Error", error);
    //   }
      
    //   );
    this.Router.navigateByUrl("/user");
  }

}
