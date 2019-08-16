import { Injectable, getDebugNode } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Config } from "../constant/config";
@Injectable({
  providedIn: 'root'
})
export class BackendService {
  URL:any;
  memberId:any;
  constructor(private Http:HttpClient, private Config: Config) { 
    this.URL = this.Config.URL;
  }

  login(data){
    return this.Http.post<any>(this.URL.login, data)
  }
  getCentreList(){
    return this.Http.post<any>("", {})
  }
  getPreferredSports(){
    //return this.Http.get<any>(this.Config.baseURL+this.Config.URL.getPreferredSports, {})
    return this.Http.get<any>(this.URL.getPreferredSports, {})
  }
  getFacilityBasedOnPreferredSprtsSelected(prefId){
    let req={
      "centerId":"2",
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
  saveMember(form,img){
    let req = {
      "centerId": "2",
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
      "facilityTypeId":form.prefSport1.value
    };
    return this.Http.post<any>(this.URL.saveMember, req)
  }
  saveBooking(fac,memID){
    let timeArray=[];
    timeArray.push(fac.timeTable.timeTableId);
    let req ={
      "memberId": memID,
      "active": true,
      "centerId": 2,
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
  getAllFacilities(){
    let req={"centerId":"2"};
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
