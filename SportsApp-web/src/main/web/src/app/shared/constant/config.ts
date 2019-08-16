export class Config {
       URL:any;
       debug:boolean=false; //make it true only for development
       baseURL:any;
       
       
       constructor(){
              if(this.debug){
                     this.baseURL = "http://13.233.160.219:8080/SportsApp/";
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
                     getAllBookings:this.baseURL+"api/booking/getAllBookingForMember/"
              }
       }
       
}
