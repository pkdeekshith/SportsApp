import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import Swal from 'sweetalert2';
import { Utility } from '../../shared/utility/utility';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor(private BackEnd:BackendService,
              private ngxService : NgxUiLoaderService,private Utility:Utility) { }
  
  memberId:any="";
  oldBookings:any=[];
  ngOnInit() {
    this.memberId = this.BackEnd.memberId;
    this.BackEnd.getUpcomingBookings(this.memberId).subscribe(
      data=>{ 
        this.oldBookings=data;
        this.ngxService.stop();

      }
    )
  }

}
