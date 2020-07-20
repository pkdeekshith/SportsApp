import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest,HttpHeaders,HttpResponse,HttpErrorResponse } from '@angular/common/http';
import { of,Observable ,throwError} from 'rxjs';
import {map,catchError,tap} from 'rxjs/operators';
import Swal from 'sweetalert2';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Router } from '@angular/router';
import {MessageService} from 'primeng/api';



@Injectable()
export class APIInterceptor implements HttpInterceptor {
  constructor(private ngxService: NgxUiLoaderService,private Router: Router ,private MessageService:MessageService){}
  intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.ngxService.start();
    let authReq;
    if(req.url && req.url.indexOf("login") < 0){
    const t=  localStorage.getItem("auth-token") ? localStorage.getItem("auth-token") : "";
    authReq = req.clone({
    headers: new HttpHeaders({
      //'Content-Type':  'application/json',
      'auth-token': t
    })
    });
  }
  else{
    //skip
    authReq = req.clone({
      headers: new HttpHeaders({
        //'Content-Type':  'application/json',
        //'auth-token': t
      })
    });
  }
  
//   return next.handle(authReq).pipe( tap(() => {},
//   (err: any) => {
//     debugger
//   if (err instanceof HttpErrorResponse) {
//     if (err.status !== 401) {
//      return;
//     }
//     this.MessageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
//     this.Router.navigateByUrl("/landing/home");
    
    
//   }
// }));
  //console.log('Intercepted HTTP call', authReq);

  return next.handle(authReq).pipe(
    map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
            this.ngxService.stop();
            //console.log('event--->>>', event);
        }
        return event;
    }),catchError((error: HttpErrorResponse) => {
      this.ngxService.stop();
      let data = {};
      data = {
          reason: error && error.error && error.error.reason ? error.error.reason : '',
          status: error.status
      };
   //   console.log(error);
   //   this.MessageService.add({severity:'success', summary:'Service Message', detail:'Via MessageService'});
   //   return throwError(error);
      if(error.status == 401){
       // return throwError(error);
       this.ngxService.stop();
      
        
        Swal.fire({
          type : 'error',
          html: '<b>'+"Invalid Session"+'</b>',
          allowOutsideClick :false,
          allowEnterKey:false,
          allowEscapeKey:false
        }).then((s)=>{
         // window.location.reload();
         window.location.pathname = "/SportsApp/";

        })
      }else{
        return throwError(error);
      }
      
    }));
    
}}