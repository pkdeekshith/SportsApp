import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Utility} from '../../shared/utility/utility';
import { BackendService} from '../../shared/service/backend.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
  
  constructor(
    private Router: Router,
    private Utility : Utility,
    public BackendService: BackendService
  ) { }

  ngOnInit() {
   
  }
  logout(){
    this.Utility.deleteSession();
    this.BackendService.memberId = undefined;
    this.BackendService.memberRole = undefined;
    localStorage.clear();
    this.Router.navigateByUrl("/landing/login");
  }
}
