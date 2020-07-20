import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-privacy',
  templateUrl: './privacy.component.html',
  styleUrls: ['./privacy.component.css']
})
export class PrivacyComponent implements OnInit {

  constructor() { }
 data=[];
  ngOnInit() {
    this.data=[
      {
        title:"Do you sell my contact details and data to others?",
        text:"Absolutely No. We never sell any of our customer and user data. This is against our policies."
      },
      {
        title:"Is my data safe and secure?",
        text:"Yes. We take security very seriously and we take many precautions to keep your data secure. We don't see and store any sensitive data like credit card numbers which will be handled by the payment gateway."
      },
      {
        title:"Can we export and download our data?",
        text:"At present No."
      }
    ]
  }

}
