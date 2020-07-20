export class Config {
       URL:any;
       debug:boolean=false; 
       baseURL:any;
       
       
       constructor(){
              this.debug = (location.hostname == "localhost")?true:false;
              if(this.debug){
                     this.baseURL = "http://15.206.200.143:8080/SportsApp/";
              }else{
                     this.baseURL = "/SportsApp/";   
              }
              this.URL = {
                     login : this.baseURL+"api/auth/login",
                     getPreferredSports :this.baseURL+"api/facility/getPreferredSports",
                     getFacilityBasedOnPreferredSprtsSelected :this.baseURL+"api/facility/getFacilityForPreferredSport",
                     verifyAvailability:this.baseURL+"api/facility/checkFacilityAvailabilty",
                     getSlotsAvailableToBook:this.baseURL+"api/timetable/getAvailableTimeTables",
                     saveMember:this.baseURL+"api/member/saveMember",
                     saveBooking:this.baseURL+"api/booking/saveBooking",
                     getMembershipType:this.baseURL+"api/member/getMemberShipTypes",
                     getAllFacilities : this.baseURL+"api/facility/getAllFacilities",
                     getsubFacilitiesOfFacility:this.baseURL+"api/facility/getSubFacilitiesForFacilities",
                     getTimeSlotsForSubFacility :this.baseURL + "api/timetable/getTimeSlotsForSubFacility",
                     getMember:this.baseURL+"api/member/getMember/",
                     getUpcomingBookings:this.baseURL+"api/booking/getUpcomingBookingForMember/",
                     getAllBookings:this.baseURL+"api/booking/getAllBookingForMember/",
                     renewMember:this.baseURL+"api/member/renewMember",
                     getBookingSummary:this.baseURL+"api/report/getBookingSummary",
                     getBookingSummaryPDF:this.baseURL+"api/report/getBookingSummaryPDF",
                     getSpotBookingForDays:this.baseURL+"api/report/getSpotBookingForDays",
                     getSpotBookingForDaysPDF:this.baseURL+"api/report/getSpotBookingForDaysPDF",
                     getAllAccountsForDays:this.baseURL+"api/report/getAllAccountsForDays",
                     getAllAccountsForDaysPDF:this.baseURL+"api/report/getAllAccountsForDaysPDF",
                     getAllBookingForDays:this.baseURL+"api/report/getAllBookingForDays",
                     getAllBookingForDaysPDF:this.baseURL+"api/report/getAllBookingForDaysPDF",

                     getConsolidatedReportByDate:this.baseURL+"api/report/getConsolidatedReportByDate",
                     getConsolidatedReportByDatePDF:this.baseURL+"api/report/getConsolidatedReportByDatePDF",

                     getConsolidatedReportByFacility:this.baseURL+"api/report/getConsolidatedReportByFacility",
                     getConsolidatedReportByFacilityPDF:this.baseURL+"api/report/getConsolidatedReportByFacilityPDF",

                     getAllFacilitiesAdmin:this.baseURL+"api/admin/getAllFacilities",
                     updateFacilities:this.baseURL+"api/admin/updateFacilities",
                     getAllSubFacilitiesAdmin:this.baseURL+"api/admin/getAllSubFacilities",
                     updateSubFacilitiesAdmin:this.baseURL+"api/admin/updateSubFacilities",
                     getPrefSportAndfacilities:this.baseURL+"api/facility/getAllFacilitiesAndPreferredSports",
                     resetPassword:this.baseURL+"api/member/changePassword",
                     forgotPassword:this.baseURL+"api/member/forgetPassword",
                     initiatePayment :this.baseURL+"api/payment/initiatePayment",
                     paymentFinal : this.baseURL+"api/payment/proceedToPayment/",
                     validateUserName:this.baseURL+"api/auth/validateUserName",
                     getMemberCred:this.baseURL+"api/auth/getMemberCred",
                     getCentreList : this.baseURL+"api/center/getAllCenters",
                     updateCenters:this.baseURL+"api/admin/updateCenters",
                     getCenterListAdmin: this.baseURL+"api/admin/getAllCenters",
                     getOnlineBookingWindow: this.baseURL+"api/admin/getOnlineBookingWindow",
                     saveBookingWindow:  this.baseURL+"api/admin/saveBookingWindow",
                     paymenthistory:this.baseURL+"api/report/getPaymentDetailsForDays",
                     paymenthistoryPDF:this.baseURL+"api/report/getPaymentDetailsForDaysPDF",

                     activeMembers:this.baseURL+"api/report/getActiveMemberContacts",
                     activeMembersPDF:this.baseURL+"api/report/getActiveMemberContactsPDF",

                     triggerSms : this.baseURL+"api/report/triggerSMS"

              }
       }
       
}
