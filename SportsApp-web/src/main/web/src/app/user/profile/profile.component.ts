import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Utility } from '../../shared/utility/utility';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

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
  editMode:boolean=false;
  constructor(private BackEnd: BackendService,
    private ngxService: NgxUiLoaderService,
    private Utility : Utility,
    private Router: Router, ) { }

  ngOnInit() {
    this.memberId = this.BackEnd.memberId;
    this.ngxService.start();
    this.BackEnd.getMember(this.memberId).subscribe(
      data=>{
        
        if(data){
          this.memberInfo=data;
          this.BackEnd.memberData = data;
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
  editMember(){
   // this.editMode = true;
    this.BackEnd.editMode = true;
    this.Router.navigateByUrl("/landing/register");
  }
  renewMember(){
    this.ngxService.start();
    this.BackEnd.renewMember(this.memberId).subscribe(
      data =>{
        this.ngxService.stop();
        if(data && data.status == "Success"){
          Swal.fire('Success!','Membership is renewed','success');
        }
      },error =>{
        this.ngxService.stop();
        Swal.fire('Error!','Failed to renew membership','error');
      }
    )
  }

}
