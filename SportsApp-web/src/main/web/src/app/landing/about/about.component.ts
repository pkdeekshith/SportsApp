import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.setBackgroundImages(4000,4);
  }
  setBackgroundImages(interval,frames){
    let int=1;
    function func(){
      document.querySelector(".about-banner").id="q"+int;
      int++;
      if(int == frames){ int=1;}

    }
    var swap = window.setInterval(func,interval);
  }
}
