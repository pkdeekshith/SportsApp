import { Component, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BackendService} from '../../shared/service/backend.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  facilityList=[];
  subFacilityList=[];
  
  WIN = 0; //To switch multiple report windows
  bookingSummary=[];
  spotBookingSummary=[];
  summaryFromDate = new Date();
  summaryToDate = new Date();
  summaryBookingMode="Online";
  spotBookinDdate = new Date();
  spotBookingMode = "Online";
  allBokingFromDate=new Date();
  allBookingToDate=new Date();
  allBookingBookingMode="Online";
  allBookingCenterId;
  allBookingFacilityId;
  allBookingSubFacilityId;
  
  allBookingForDay=[];
  allAccountFromDate=new Date();
  allAccountToDate = new Date();
  allAccountBookingMode="Online";
  allAccountCenterId;
  allAccountFacilityId;
  allAccountSubFacilityId;
  allAccountPayment;
  allAccountForDay=[];
  
  consolidatedByDateFromDate=new Date();
  consolidatedByDateToDate = new Date();
  consolidatedByDateBookingMode="Online";
  consolidatedByDateCenterId;
  consolidatedByDateFacilityId;
  consolidatedByDate=[];
  consolidatedByFacilityFromDate=new Date();
  consolidatedByFacilityToDate=new Date();
  consolidatedByFacilityBookingMode="Online";
  consolidatedByFacilityCenterId;
  consolidatedByFacilityFacilityId;
  consolidatedByFacility=[];

  constructor(private ngxService: NgxUiLoaderService,
    private BackEnd : BackendService,) { }
    
    ngOnInit() {
      this.getBookingSummary();
    }
    getBookingSummary(){
      this.ngxService.start();
      let req={
        "fromDate":this.summaryFromDate.toISOString().split("T")[0],
        "endDate":this.summaryToDate.toISOString().split("T")[0],
        "bookingApp":this.summaryBookingMode
      };
      this.BackEnd.getBookingSummary(req).subscribe(
        data=>{
          this.ngxService.stop();
          if(data.status == "Success"){
            this.bookingSummary = data.data;
          }else{
            this.bookingSummary=[];
          }
        },
        error=>{
          this.ngxService.stop();
          this.bookingSummary=[];
          console.log(error);
        }
        )
      }
      getSpotBookingSummary(){
        this.ngxService.start();
        let req = {
          "date": this.spotBookinDdate.toISOString().split("T")[0],
          "bookingApp": this.spotBookingMode,
          "sessionType":""
        }
        this.BackEnd.getSpotBookingForDays(req).subscribe(
          data=>{
            this.ngxService.stop();
            if(data.status =="Success"){
              this.spotBookingSummary = data.data;

            }else{
              this.spotBookingSummary=[];
            }
          },
          error=>{
            this.ngxService.stop();
            this.spotBookingSummary=[];
            console.log(error);
          }
          )
        }
        getAllBookingForDays(){
          this.ngxService.start();
          let req = {
            "fromDate": this.allBokingFromDate.toISOString().split("T")[0],
            "endDate": this.allBookingToDate.toISOString().split("T")[0],
            "bookingApp":this.allBookingBookingMode,
            "centerId":this.allBookingCenterId,
            "facilityId":this.allBookingFacilityId,
            "subfacilityId":this.allBookingSubFacilityId
          }
          this.BackEnd.getAllBookingForDays(req).subscribe(
            data=>{
              this.ngxService.stop();
              if(data.status =="Success"){
                this.allBookingForDay = data.data;
              }else{
                this.allBookingForDay=[];
              }
              
            },
            error=>{
              this.ngxService.stop();
              this.allBookingForDay=[];
              console.log(error);
            }
            )
          }
          getAllAccountForDays(){
            this.ngxService.start();
            let req = {
              "fromDate":this.allAccountFromDate.toISOString().split("T")[0],
              "endDate":this.allAccountToDate.toISOString().split("T")[0],
              "bookingApp":this.allAccountBookingMode,
              "centerId":this.allAccountCenterId,
              "facilityId":this.allAccountFacilityId,
              "subfacilityId":this.allAccountSubFacilityId,
              "typeOfPayment":this.allAccountPayment
            }
            this.BackEnd.getAllAccountsForDays(req).subscribe(
              data=>{
                this.ngxService.stop();
                if(data.status=="Success"){
                  this.allAccountForDay = data.data;
                }else{
                  this.allAccountForDay=[];
                }
                
              },
              error=>{
                this.ngxService.stop();
                this.allAccountForDay=[];
                console.log(error);
              }
              )
            }
            getConsolidatedReportByDate(){
              this.consolidatedByDate=[];
              this.ngxService.start();
              let req = {
                "fromDate":this.consolidatedByDateFromDate.toISOString().split("T")[0],
                "endDate":this.consolidatedByDateToDate.toISOString().split("T")[0],
                "bookingApp":this.consolidatedByDateBookingMode,
                "centerId":this.consolidatedByDateCenterId,
                "facilityId":this.consolidatedByDateFacilityId
              }
              this.BackEnd.getConsolidatedReportByDate(req).subscribe(
                data=>{
                  this.ngxService.stop();
                  if(data.status=="Success"){
                    this.consolidatedByDate = data.data;
                  }
                  
                },
                error=>{
                  this.ngxService.stop();
                  console.log(error);
                }
                )
              }
              getConsolidatedReportByFacility(){
                this.consolidatedByFacility=[];
                this.ngxService.start();
                let req = {
                  "fromDate":this.consolidatedByFacilityFromDate.toISOString().split("T")[0],
                  "endDate":this.consolidatedByFacilityToDate.toISOString().split("T")[0],
                  "bookingApp":this.consolidatedByFacilityBookingMode,
                  "centerId":this.consolidatedByFacilityCenterId,
                  "facilityId":this.consolidatedByFacilityFacilityId
                }
                this.BackEnd.getConsolidatedReportByFacility(req).subscribe(
                  data=>{
                  this.ngxService.stop();
                  if(data.status=="Success"){
                    this.consolidatedByFacility = data.data;
                  }
                  },
                  error=>{
                  this.ngxService.stop();
                  console.log(error);
                  }
                )
              }
              setWIN(id){
                this.facilityList=[];
                this.subFacilityList=[];
                this.WIN = id;
              }
              handleCenterSelection(center,winID){
                this.BackEnd.getAllFacilities().subscribe(
                  data=>{
                    this.facilityList = data;
                  },
                  error=>{

                  }
                )
              }
              handleFacilitySelection(id,winID){
                if(id=="All"){
                  this.subFacilityList=[];
                  return;
                }
                this.BackEnd.getsubFacilitiesOfFacility(id).subscribe(
                  data=>{
                    this.subFacilityList = data[0].subFacility;
                  }
                )
              }
            }
            