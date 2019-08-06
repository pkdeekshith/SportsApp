import { Component, OnInit ,ElementRef} from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {
  availableSlots : any = ["10 AM - 11 AM","10 AM - 11 AM","10 AM - 11 AM","10 AM - 11 AM","10 AM - 11 AM","10 AM - 11 AM","10 AM - 11 AM"];
  constructor(private BackEnd : BackendService,
              private host : ElementRef) {
    let centrelist : any = [];
    let faciltyList : any =[];
    let subFacilityList :any=[];
   }
  ngOnInit() {
    
    


    // this.BackEnd.getCentreList()
    // .subscribe(
    //     data  => {
    //       console.log("POST Request is successful ", data);
    //     },
    //     error  => {
    //       console.log("Error", error);
    //     }
        
    //     );
  }
  getSlots(){
    alert("dfdf");
  }
  slotClicked(){
    let eP;
    let e= event.target as HTMLInputElement;
    if(e.id =="tBox") {eP = e;}
    else { eP =e.parentElement; }
    if(eP.classList.contains("selected")){
      eP.classList.remove("selected")
    }else{
      eP.classList.add("selected");
    }
  }
 
}
