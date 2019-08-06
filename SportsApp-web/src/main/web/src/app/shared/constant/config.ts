export class Config {
      URL:any;
      baseURL = "/SportsApp/";
     constructor(){
        this.URL = {
               login : this.baseURL+"login",
               getPreferredSports :this.baseURL+"api/facility/getPreferredSports",
               getFacilityBasedOnPreferredSprtsSelected :this.baseURL+"api/facility/getFacilityForPreferredSport",
               verifyAvailability:this.baseURL+"api/facility/checkFacilityAvailabilty",
               getSlotsAvailableToBook:this.baseURL+"api/timetable/getAvailableTimeTables",
               saveMember:this.baseURL+"api/member/saveMember",
               saveBooking:this.baseURL+"api/booking/saveBooking",
               getMembershipType:this.baseURL+"api/member/getMemberShipTypes"
        }
     }

}
