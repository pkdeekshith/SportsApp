import { Component, OnInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BackendService} from '../../shared/service/backend.service';
import {MessageService} from 'primeng/api';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  facilityList=[];
  subFacilityList=[];
  
  summaryBookingCenterId="";
  spotBookinCenter="";
  
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
  allBookingCenterId="";
  allBookingFacilityId;
  allBookingSubFacilityId;
  
  allBookingForDay=[];
  allAccountFromDate=new Date();
  allAccountToDate = new Date();
  allAccountBookingMode="Online";
  allAccountCenterId="";
  allAccountFacilityId;
  allAccountSubFacilityId;
  allAccountPayment;
  allAccountForDay=[];
  
  consolidatedByDateFromDate=new Date();
  consolidatedByDateToDate = new Date();
  consolidatedByDateBookingMode="Online";
  consolidatedByDateCenterId="";
  consolidatedByDateFacilityId;
  consolidatedByDate=[];
  consolidatedByFacilityFromDate=new Date();
  consolidatedByFacilityToDate=new Date();
  consolidatedByFacilityBookingMode="Online";
  consolidatedByFacilityCenterId="";
  consolidatedByFacilityFacilityId;
  consolidatedByFacility=[];
  
  totalAmount=0; 
  centerList=[];
  
  //pay table
  payFromDate =new Date();
  payToDate = new Date();
  payBookingMode="Online";
  payStatus="All";
  paymentSummary=[];

  activeUsersCenter="0";
  activeUsers=[];
  userMessage;
  
  constructor(private ngxService: NgxUiLoaderService,
    private BackEnd : BackendService,private MessageService:MessageService) { }
    calculateTotal(arr){
      this.totalAmount = 100;
    }
    ngOnInit() {
      // this.getBookingSummary();
      this.ngxService.start();
      this.BackEnd.getCentreList().subscribe(
        data=>{
          this.ngxService.stop();
          this.centerList = data;
        },error=>{
          this.ngxService.stop();
          console.log(error);
          
        }
        )
      }
      getBookingSummary(flag){
        this.ngxService.start();
        let req={
          "fromDate":this.summaryFromDate.toISOString().split("T")[0],
          "endDate":this.summaryToDate.toISOString().split("T")[0],
          "bookingApp":this.summaryBookingMode,
          "center":this.summaryBookingCenterId
        };
        if(flag){
          this.BackEnd.getBookingSummaryPDF(req).subscribe(
            data=>{
              this.ngxService.stop();
              let fileURL = URL.createObjectURL(data);
              window.open(fileURL);
            },error=>{
              this.ngxService.stop();
            }
          )
        }
        else{
          this.BackEnd.getBookingSummary(req).subscribe(
            data=>{
              this.ngxService.stop();
              if(data.status == "Success"){
                this.bookingSummary = data.data;
                this.bookingSummary.push({
                  "date":'TOTAL',
                  "memberRegistration": data.memberRegistration,
                  renewal: data.renewal,
                  booking: data.booking,
                  quickBooking:data.quickBooking,
                  total:data.total
                });
  
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
        
        }
        getSpotBookingSummary(flag){

          this.ngxService.start();
          let req = {
            "date": this.spotBookinDdate.toISOString().split("T")[0],
            "bookingApp": this.spotBookingMode,
            "sessionType":"",
            "center":this.spotBookinCenter
          }
          if(flag){
            this.BackEnd.getSpotBookingForDaysPDF(req).subscribe(
              data=>{
                this.ngxService.stop();
                let fileURL = URL.createObjectURL(data);
                window.open(fileURL);
              },error=>{
                this.ngxService.stop();
              }
            )
          }else{
            this.BackEnd.getSpotBookingForDays(req).subscribe(
              data=>{
                this.ngxService.stop();
                if(data.status =="Success"){
                  this.spotBookingSummary = data.data;
                  this.spotBookingSummary.push({
                    price:'TOTAL',
                    amount : data.totalAmount
                  })
                }else{
                  this.spotBookingSummary=[];
                }
              },
              error=>{
                this.ngxService.stop();
                this.spotBookingSummary=[];
                alert("Server Error");
                console.log(error);
              }
              )
          }
          
          }
          getAllBookingForDays(flag){
            this.ngxService.start();
            let req = {
              "fromDate": this.allBokingFromDate.toISOString().split("T")[0],
              "endDate": this.allBookingToDate.toISOString().split("T")[0],
              "bookingApp":this.allBookingBookingMode,
              "centerId":this.allBookingCenterId,
              "facilityId":this.allBookingFacilityId,
              "subfacilityId":this.allBookingSubFacilityId
            }
            if(flag){
              this.BackEnd.getAllBookingForDaysPDF(req).subscribe(
                data=>{
                  this.ngxService.stop();
                  let fileURL = URL.createObjectURL(data);
                  window.open(fileURL);
                },error=>{
                  this.ngxService.stop();
                }
              )
            }else{
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
            
            }
            getAllAccountForDays(flag){
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
              if(flag){
                this.BackEnd.getAllAccountsForDaysPDF(req).subscribe(
                  data=>{
                    this.ngxService.stop();
                    let fileURL = URL.createObjectURL(data);
                    window.open(fileURL);
                  },error=>{
                    this.ngxService.stop();
                  }
                )
              }else{
                this.BackEnd.getAllAccountsForDays(req).subscribe(
                  data=>{
                    this.ngxService.stop();
                    if(data.status=="Success"){
                      this.allAccountForDay = data.data;
                      this.allAccountForDay.push({'receiptType':'TOTAL','debitAmount':data.totalDebitAmount,
                      'creditAmount':data.totalCreditAmount})
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
              
              }
              getConsolidatedReportByDate(flag){
                this.consolidatedByDate=[];
                this.ngxService.start();
                let req = {
                  "fromDate":this.consolidatedByDateFromDate.toISOString().split("T")[0],
                  "endDate":this.consolidatedByDateToDate.toISOString().split("T")[0],
                  "bookingApp":this.consolidatedByDateBookingMode,
                  "centerId":this.consolidatedByDateCenterId,
                  "facilityId":this.consolidatedByDateFacilityId
                }
                if(flag){
                  this.BackEnd.getConsolidatedReportByDatePDF(req).subscribe(
                    data=>{
                      this.ngxService.stop();
                      let fileURL = URL.createObjectURL(data);
                      window.open(fileURL);
                    },error=>{
                      this.ngxService.stop();
                    }
                  )
                }else{
                  this.BackEnd.getConsolidatedReportByDate(req).subscribe(
                    data=>{
                      this.ngxService.stop();
                      if(data.status=="Success"){
                        this.consolidatedByDate = data.data;
                        this.consolidatedByDate.push({
                          facility:'TOTAL',
                          amount:data.totalAmount
                        });
                      }else{
                        this.consolidatedByDate=[];
                      }
                      
                    },
                    error=>{
                      this.consolidatedByDate=[];
                      this.ngxService.stop();
                      console.log(error);
                    }
                    )
                }
                
                }
                getConsolidatedReportByFacility(flag){
                  this.consolidatedByFacility=[];
                  this.ngxService.start();
                  let req = {
                    "fromDate":this.consolidatedByFacilityFromDate.toISOString().split("T")[0],
                    "endDate":this.consolidatedByFacilityToDate.toISOString().split("T")[0],
                    "bookingApp":this.consolidatedByFacilityBookingMode,
                    "centerId":this.consolidatedByFacilityCenterId,
                    "facilityId":this.consolidatedByFacilityFacilityId
                  }
                  if(flag){
                    this.BackEnd.getConsolidatedReportByFacilityPDF(req).subscribe(
                      data=>{
                        this.ngxService.stop();
                        let fileURL = URL.createObjectURL(data);
                        window.open(fileURL);
                      },error=>{
                        this.ngxService.stop();
                      }
                    )
                  }else{
                    this.BackEnd.getConsolidatedReportByFacility(req).subscribe(
                      data=>{
                        this.ngxService.stop();
                        if(data.status=="Success"){
                          this.consolidatedByFacility = data.data;
                          this.consolidatedByFacility.push({
                            facility:'TOTAL',
                            amount:data.totalAmount
                          });
                        }else{
                          this.consolidatedByFacility =[];
                        }
                      },
                      error=>{
                        this.consolidatedByFacility =[];
                        alert("Server Error");
                        this.ngxService.stop();
                        console.log(error);
                      }
                      )
                  }
                 
                  }
                  setWIN(id){
                    this.facilityList=[];
                    this.subFacilityList=[];
                    this.WIN = id;
                  }
                  handleCenterSelection(center,winID){
                    this.BackEnd.getAllFacilities(center.toString()).subscribe(
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
                      getPaymentHistory(flag){
                         let req= {
                           "fromDate":this.payFromDate.toISOString().split("T")[0],
                           "endDate":this.payToDate.toISOString().split("T")[0],
                           "bookingApp": this.payBookingMode,
                           "status": this.payStatus 
                          };
                          this.ngxService.start();
                          if(flag){
                            this.BackEnd.getPaymentHistoryPDF(req).subscribe(
                              data=>{
                                this.ngxService.stop();
                                let fileURL = URL.createObjectURL(data);
                                window.open(fileURL);
                              },error=>{
                                this.ngxService.stop();
                              }
                            )
                          }else{
                            this.BackEnd.getPaymentHistory(req).subscribe(
                              data=>{
                                this.ngxService.stop();
                                if(data && data.data){
                                  this.paymentSummary = data.data;
                                  this.paymentSummary.push({
                                    memberName : 'TOTAL',
                                    totalAmount:data.totalAmount
                                  });
                                }else{
                                 
                                }
                              },
                              error=>{
                                this.paymentSummary =[];
                                this.ngxService.stop();
                                alert("Server Error");
                              }
                            )
                          }
                          

                      }
                      getActiveUsers(flag){
                        let req={ "centerId": this.activeUsersCenter };
                        this.ngxService.start();
                        if(flag){
                          this.BackEnd.getActiveUsersPDF(req).subscribe(
                            data=>{
                              this.ngxService.stop();
                              let fileURL = URL.createObjectURL(data);
                              window.open(fileURL);
                            },error=>{
                              this.ngxService.stop();
                            }
                          )
                        }else{
                          this.BackEnd.getActiveUsers(req).subscribe(
                            data=>{
                              this.ngxService.stop();
                              this.activeUsers = data;
                            },
                            error=>{
                              this.ngxService.stop();
                              alert("Server Error");
                            }
                          )
                        }
                          
                      }
                      sendUserMessage(){
                        let req={"centerId": this.activeUsersCenter,
                                  "message": this.userMessage
                        };
                        this.ngxService.start();
                          this.BackEnd.sendUserMessage(req).subscribe(
                            data=>{
                              this.ngxService.stop();
                              this.MessageService.clear();
                              this.MessageService.add({key: 'successToast', severity:'success', summary: '', detail: 'Success'});
                            },
                            error=>{
                              this.ngxService.stop();
                              alert("Server Error");
                            }
                          )
                      }
                    }
                    