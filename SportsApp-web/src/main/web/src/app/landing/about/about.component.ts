import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor() { }
  swap:any;
  hideFirst = true;
  ngOnInit() {
    this.setBackgroundImages(4000,4);
  }
  ngOnDestroy(){
    window.clearInterval(this.swap);
  }
  setBackgroundImages(interval,frames){
    let int=1;
    function func(){
      document.querySelector(".about-banner").id="q"+int;
      int++;
      if(int == frames){ int=1;}

    }
     this.swap = window.setInterval(func,interval);
  }
}
