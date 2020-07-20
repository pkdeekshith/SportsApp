import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {

  constructor( ) { }
  data=[];
  price=[];
  reg=[];
  pay=[];
  ngOnInit() {
    this.data= [{
      "title":"What is SPOT?",
      "text":"SPOT is a sports management and Ticket booking platform that helps people to accessing all the Sports facilities across Kerala. Now it acts in live for Jimmy George Sports Hub, Thiruvananthapuram. Public can access the facilities like Badminton Court, Swimming Pool, Basketball Court,  Gymnastics, Sports Life Fitness Centre etc. Booking, Slot Allotment, Payment methods all are available only through online."
    },
    {
      title:'What sports does SPOT support?',
      text:'Now it acts in live for Jimmy George Sports Hub, Thiruvananthapuram. Public can access the facilities like Badminton Court, Swimming Pool, Basketball Court,  Gymnastics, Sports Life Fitness Centre etc. In future it will be extended to all sports facilities across Kerala.'
    },
    {
      title:'How long does it take to set up my account?',
      text:"It depends on your data. It typically takes less than 10 minutes to start with."
    },
    {
      title:'Any additional charges or support fees?',
      text:"Absolutely No. We don't charge for the support or even consulting."
    },
    {
      title:"How can a player signup?",
      text:"Typically players can signup through website when they register for a booking, as part of the registration process."
    },
    {
      title:"Do I need to install any software?",
      text:"No. SPOT is a web-based application that you can access with all major internet browsers including on smart phones"
    },
    {
      title:"Do I need any technical knowledge?",
      text:"You just need basic computer knowledge to use SPOT. That's it"
    },
    {
      title:"SPOT is owned by?",
      text:"SPOT is owned by Directorate of Sports & Youth Affairs, Government of Kerala."
    },
    {
      title:"How many languages does SPOT support?",
      text:"SPOT is owned by Directorate of Sports & Youth Affairs, Government of Kerala."
    }];
    this.price =[
      {
        title:"How much does SPOT subscription costs?",
        text:"There is no subscription fee. only approved fee tariff for your concerned sports for slot booking."
      },
      {
        title:"Can I cancel my Booking?",
        text:"Yes. You can cancel your booking at any time"
      },{
        title:"Does the payment method completed can I start training?",
        text:"When you pay the booking fee, your account will become a paid account automatically and you can startthe training based on your allotted slot and time."
      },
      {
        title:"Can I upgrade my monthly plan to annual?",
        text:"No. At present only monthly booking option is available"
      },
      {
        title:"Any additional charges or support fees?",
        text:"Absolutely No. The fee includes everything. We don't charge for the support or even consulting"
      },
      {
        title:"Are there any long-term contracts/booking plans?",
        text:"No. There are no mandatory long term contracts/booking plans."
      },
      {
        title:"What payment modes do you support?",
        text:"We accept all major credit/Debit cards."
      },
      {
        title:"How can I get a discount?",
        text:"At present Concessional rates are applicable only for International/National athletes."
      }
    ]
    this.reg=[
      {
        title:"How can a parent register their kids for a program?",
        text:"Parents can check out all the available programs and then can register for the right programs."
      },
      {
        title:"Can I pay both online and offline payments?",
        text:"Yes. You can do it by selecting the right options while setting up the programs"
      },
      {
        title:"How do I create instalments?",
        text:"We are not allowing instalment payment. You should pay the full amount fee for the month."
      },
      {
        title:"Can registrants upload the documents?",
        text:"No, at the same time you can fill up the necessary information to the field while booking a program."
      },
      {
        title:"How do I track my unpaid registrations?",
        text:"To track unpaid registrations, please navigate to Payments > Unpaid Registrations."
      },
      {
        title:"How do I collect recurring payments?",
        text:"While booking, in the Fee & Payments Options section, define the payment type as Monthly Payment and follow the steps"
      },
      {
        title:"Does SPOT support family member discounts?",
        text:"At present No"
      },
      {
        title:"How do I view all my registrations?",
        text:"To view all registrations please navigate to Registration > All Registrations"
      }
    ]
    this.pay=[
      {
        title:"What are the payment processing charges?",
        text:"The standard payment processing charges are based on banking services"
      },
      {
        title:"When do payments get billed and how will they show up in our bank account?",
        text:"All the payments are billed and processed and the money will be debited from your bank account immediately."
      },
      {
        title:"Can I do both online and offline payments?",
        text:"Yes. You can do it by selecting the right options while choosing the programs."
      },
      {
        title:"How do I generate payment reports?",
        text:""
      },
      {
        title:"How do I track my unpaid registrations?",
        text:"To track unpaid registrations, please navigate to ."
      },
      {
        title:"What happens if there is fraudulent activity on our payments?",
        text:"Please let us know first. We will work with you and the payment gateway to resolve the issue."
      },
      {
        title:"How do I collect recurring payments?",
        text:"While creating a program, in the Fee & Payments Options section, define the payment type as Monthly Payment and follow the steps."
      }
    ]

  }

}
