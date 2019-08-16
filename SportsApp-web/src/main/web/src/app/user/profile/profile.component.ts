import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  memberInfo:any={};
  prefSport:any="";
  memberId:any="";
  activeBookings:any=[];
  cars = [
    {"brand": "VW", "year": 2012, "color": "Orange", "vin": "dsad231ff"},
    {"brand": "Audi", "year": 2011, "color": "Black", "vin": "gwregre345"},
    {"brand": "Renault", "year": 2005, "color": "Gray", "vin": "h354htr"},
    {"brand": "BMW", "year": 2003, "color": "Blue", "vin": "j6w54qgh"},
    {"brand": "Mercedes", "year": 1995, "color": "Orange", "vin": "hrtwy34"},
    {"brand": "Volvo", "year": 2005, "color": "Black", "vin": "jejtyj"},
    {"brand": "Honda", "year": 2012, "color": "Yellow", "vin": "g43gr"},
    {"brand": "Jaguar", "year": 2013, "color": "Orange", "vin": "greg34"},
    {"brand": "Ford", "year": 2000, "color": "Black", "vin": "h54hw5"},
    {"brand": "Fiat", "year": 2013, "color": "Red", "vin": "245t2s"}
];
  constructor(private BackEnd: BackendService,
    private ngxService: NgxUiLoaderService ) { }

  ngOnInit() {
    this.memberId = this.BackEnd.memberId;
    this.ngxService.start();
    this.BackEnd.getMember(this.memberId).subscribe(
      data=>{
        
        if(data){
          this.memberInfo=data;
          let e=[];
          data.facilityType.forEach(function(i){e.push(i.facilityTypeName)});
          this.prefSport = e.join();
          this.BackEnd.getUpcomingBookings(this.memberId).subscribe(
            data=>{
              
              this.activeBookings=data;
              this.ngxService.stop();

            }
          )
        }else{
          alert("Server error");
        }
        
      },error=>{
        alert("Server error");
        this.ngxService.stop();
      }
    )
  }

}
