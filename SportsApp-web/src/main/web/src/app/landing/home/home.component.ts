import { Component, OnInit } from '@angular/core';
import { interval } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {
    
   }
   swap: any;
  ngOnInit() {
    this.setBackgroundImages(4000,4);
  }
  ngOnDestroy(){
    window.clearInterval(this.swap);
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
}
