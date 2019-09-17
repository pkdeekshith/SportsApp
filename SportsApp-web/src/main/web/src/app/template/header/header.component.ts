import { Component, OnInit } from '@angular/core';
import { BackendService} from '../../shared/service/backend.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private BackEnd:BackendService) { }

  ngOnInit() {
  }

}
