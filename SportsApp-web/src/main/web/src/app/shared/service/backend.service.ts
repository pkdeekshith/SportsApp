import { Injectable, getDebugNode } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Config } from "../constant/config";
@Injectable({
  providedIn: 'root'
})
export class BackendService {
  URL:any;
  memberId:any;
  editMode:any;
  memberData:any;
  memberRole:any;
  preRegData:any;
  exploredFacility:any;
  facilityToValidate:any;
  cardAmount=0;
  searchCenter:any;
  centerIdFromLogin:any;
  constructor(private Http:HttpClient, private Config: Config) { 
    this.URL = this.Config.URL;
  }

  login(data){
    return this.Http.post<any>(this.URL.login, data,{observe: 'response' as 'body'})
  }
  getCentreList(){
    return this.Http.get<any>(this.URL.getCentreList, {})
  }
  getCenterListAdmin(){
    return this.Http.get<any>(this.URL.getCenterListAdmin, {})
  }
  getOnlineBookingWindow(){
    return this.Http.get<any>(this.URL.getOnlineBookingWindow, {})
  }
  saveBookingWindow(req){
    return this.Http.post<any>(this.URL.saveBookingWindow, req)
  }
  getPreferredSports(){
    //return this.Http.get<any>(this.Config.baseURL+this.Config.URL.getPreferredSports, {})
    return this.Http.get<any>(this.URL.getPreferredSports, {})
  }
  getFacilityBasedOnPreferredSprtsSelected(id,prefId){
    let req={
      "centerId": id.toString(),
      "facilityTypeId":prefId.map(String)
    };
    return this.Http.post<any>(this.URL.getFacilityBasedOnPreferredSprtsSelected, req)
  }
  verifyAvailability(fac){
    //let req =  {"facility":["10","8"]};
    let req =  {"facility": fac.map(String)};
    return this.Http.post<any>(this.URL.verifyAvailability, req)
  }
  getSlotsAvailableToBook(data){
    let req= {
      "facility": data.map(String)   
     // "facility":["7","9","10"]
             };
    return this.Http.post<any>(this.URL.getSlotsAvailableToBook, req)
  }
  saveMember(form,img,id){
    let req:any={};
    let temp:any;
    let center;
    if(this.facilityToValidate){
      center = this.facilityToValidate.centreId;
    }
    if(this.editMode){
      temp= [this.memberData.facilityType[0].facilityTypeId];
      center = this.memberData.centreId;
    }else{
      temp=this.preRegData.prefsport;
    }
     req = {
      "centerId": center.toString(),
      "memberName": form.memberName.value,
      "memberPhoto": img,
      "isStudent": form.student.value =="Yes" ? true :false,
      "isGovt": form.govt.value =="Yes" ? true :false,
      "isCoaching": form.coaching.value =="Yes" ? true :false,
      "memberIdProof": form.idNumber.value,
      "memberIdProofType": form.idType.value,
      "gender": form.gender.value,
      "dob": this.getDOB(form.dateOfBirth.value),
      "memberContactNo": form.mobNumber.value,
      "email": form.email.value,
      "street": form.address.value,
      "city": form.city.value,
      "district": form.district.value,
      "state": form.state.value,
      "country": form.country.value,
      "pincode": form.pin.value,
      "fatherName": form.guardName.value,
      "age": this.getAge(form.dateOfBirth.value),
      "memberShipTypeId": form.memID.value,
      //"facilityTypeId":form.prefSport1.value
      "facilityTypeId": temp
    };
    if(id.length){
      req.memberId = parseInt(id);
    }
    return this.Http.post<any>(this.URL.saveMember, req)
  }
  saveBooking(fac,memID){
    let timeArray=[];
    timeArray.push(fac.timeTable.timeTableId);
    let cid;
     if(this.exploredFacility){ //login user come form sport html
       cid = this.exploredFacility.centreId;
     }else{
       cid = this.facilityToValidate.centreId; //new user come from facility html 
    }
    let req ={
      "memberId": memID.toString(),
      "active": true,
      "centerId": cid.toString(),
      "facilityId": fac.facility.facilityId,
      "subFacilityId": fac.subFacility.subFacilityId,
      "timeTableId": timeArray,
      "otherMemberId": []
    }
    return this.Http.post<any>(this.URL.saveBooking, req)
  }
  getMemberShiptype(){
    return this.Http.get<any>(this.URL.getMembershipType, {})
  }
  getAllFacilities(data){
    if(!data) { data = "0" };
    let req={"centerId": data};
    return this.Http.post<any>(this.URL.getAllFacilities, req)
  }
  getsubFacilitiesOfFacility(facilityId){
    let req={"facility": [facilityId].map(String)};
    return this.Http.post<any>(this.URL.getsubFacilitiesOfFacility, req)
  }
  getTimeSlotsForSubFacility(id){
    let req = {"subFacility":[id].map(String)}
    return this.Http.post<any>(this.URL.getTimeSlotsForSubFacility, req)
  }
  getMember(id){
    return this.Http.get<any>(this.URL.getMember+id, {})
  }
  getUpcomingBookings(id){
    //return this.Http.get<any>(this.URL.getUpcomingBookings+id, {})
    return this.Http.get<any>(this.URL.getUpcomingBookings+id, {})
  }
  getBookingsHistory(id){
    return this.Http.get<any>(this.URL.getUpcomingBookings+id, {})
  }
  renewMember(id){
    let req = {
      "centerId":2,
      "memberId":parseInt(id)
    };
    return this.Http.post<any>(this.URL.renewMember, req)
  }
  getBookingSummary(req){
    return this.Http.post<any>(this.URL.getBookingSummary, req)
  }
  getSpotBookingForDays(req){
    return this.Http.post<any>(this.URL.getSpotBookingForDays, req)
  }
  getAllAccountsForDays(req){
    return this.Http.post<any>(this.URL.getAllAccountsForDays, req)
  }
  getAllBookingForDays(req){
    return this.Http.post<any>(this.URL.getAllBookingForDays, req)
  }
  getConsolidatedReportByDate(req){
    return this.Http.post<any>(this.URL.getConsolidatedReportByDate, req)
  }
  getConsolidatedReportByFacility(req){
    return this.Http.post<any>(this.URL.getConsolidatedReportByFacility, req)
  }
  getFacilitiesForAdmin(id){
    let req={"centerId":id.toString()};
    return this.Http.post<any>(this.URL.getAllFacilitiesAdmin, req)
  }
  getPreferedSportAndFacilities(id){
    let req={"centerId":id};
    return this.Http.post<any>(this.URL.getPrefSportAndfacilities, req)
  }
  updateFacilities(req){
    return this.Http.post<any>(this.URL.updateFacilities, req)
  }
  updateCenters(req){
    return this.Http.post<any>(this.URL.updateCenters, req)
  }
  getAllSubFacilitiesAdmin(facilityId){
    let req={"facility": [facilityId].map(String)};
    return this.Http.post<any>(this.URL.getAllSubFacilitiesAdmin, req)
  }
  updateSubFacilitiesAdmin(req){
    return this.Http.post<any>(this.URL.updateSubFacilitiesAdmin, req)
  }
  resetPassword(req){
    return this.Http.post<any>(this.URL.resetPassword, req)
  }
  forgotPassword(req){
    return this.Http.post<any>(this.URL.forgotPassword, req);
  }
  initiatePayment(req){
   
      return this.Http.post<any>(this.URL.initiatePayment, req);  
  }
  validateUserName(req){
    return this.Http.post<any>(this.URL.validateUserName, req);  
  }
  getMemberCred(req){
    return this.Http.post<any>(this.URL.getMemberCred, req)
  }
  //helper functions-move to utility later
  getDOB(date){
    let d= new Date(date);
    let month = d.getMonth();
    let ar=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    return d.getDate()+" "+ar[month]+" "+d.getFullYear();
  }
  getAge(dateString) {
    let today = new Date();
    let birthDate = new Date(dateString);
    let age = today.getFullYear() - birthDate.getFullYear();
    let m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    return age;
}

}
