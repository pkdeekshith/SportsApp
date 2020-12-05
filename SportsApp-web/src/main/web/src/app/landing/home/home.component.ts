import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { Router } from '@angular/router';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private Router: Router,private BackEnd : BackendService,private ngxService: NgxUiLoaderService) {
    
   }
   swap: any;
   centerList=[];
   searchedCenter:any;
  ngOnInit() {
    //this.centerList = [{id:2,name:'jimmy'},{id:3,name:'Newone'}];
    
   // this.ngxService.start();
    this.BackEnd.getCentreList().subscribe(
      data=>{
       // this.ngxService.stop();
        this.centerList = data;
        this.searchedCenter = data[0];
      },error=>{
       // this.ngxService.stop();
        console.log(error);
        
      }
    )

  }
  ngOnDestroy(){
    //window.clearInterval(this.swap);
  }
  setBackgroundImages(interval,frames){
    let int=1;
    function func(){
      document.querySelector(".home-banner").id="t"+int;
      int++;
      if(int == frames){ int=1;}

    }
     this.swap = window.setInterval(func,interval);
  }
  goToJoinPage(){
    this.BackEnd.searchCenter = this.searchedCenter;
    this.Router.navigateByUrl("/landing/facility");
  }
}
