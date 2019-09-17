export class Utility {
    slotFormat(t:any){
        let tail="AM";
        let hour:any= t/60;
        if(hour>12){hour = hour-12; tail="PM";}
        let minute:any = t%60;
        //if(minute ==0) { minute ="00";}
        return parseInt(hour) +":"+(parseInt(minute)==0?"00":parseInt(minute))+tail;
      }
      saveSession(id){
        sessionStorage.setItem("userId",id);
      }
      getSession(){
        if(sessionStorage.getItem("userId") == null){
          return false;
        }else{
          return sessionStorage.getItem("userId");
        }
      }
      deleteSession(){
        sessionStorage.clear();
      }

    constructor(){}
    
}
