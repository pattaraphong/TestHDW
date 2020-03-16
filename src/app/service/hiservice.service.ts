import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
//import { Observable } from ‘rxjs/Observable’;
import {Observable, throwError} from 'rxjs';
import { ActiveDescendantKeyManager } from '@angular/cdk/a11y';
import { getMatIconNameNotFoundError } from '@angular/material/icon';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class HiserviceService {
  public API = '//localhost:8080';

  //private ServiceUrl = 'http://localhost:8080/nameRegister' //getPart จากbackend
  constructor(private http: HttpClient) { }
  private messageSource = new BehaviorSubject(JSON);
  currentMessage = this.messageSource.asObservable();

  getHi():Observable<any>{
   return this.http.get(this.API + '/nameRegister');

  }

  addName(inputName: string, inputEmail: string, inputPhone: number,job: number, detail: string, status: number){
    return this.http.post(this.API + '/nameRegister/addNameRegister/'+inputName+'/'+inputEmail+'/'+inputPhone+'/'+job+'/'+detail+'/'+status,{
    });

  }
  getJob():Observable<any>{
    return this.http.get(this.API + '/jobPosition');

   }
  getstatus():Observable<any>{
    return this.http.get(this.API + '/statusUser');

   }
  //getSearchJob():Observable<any>{
  //  return this.http.get(this.API + '/SearchJob');
  //}

  changeMessage(message) {
    this.messageSource.next(message)
  }
  deleteName(idName: number){
    return this.http.delete(this.API + '/nameRegister/'+idName,{
    });
  }


  getSearchName(Name: string){
    //console.log("GatPart "+Name);
    return this.http.get(this.API + '/SearchName/'+Name,{
    });
  }

  getSearchEmail(Email: string){
    //console.log("GatPart "+Email);
    return this.http.get(this.API + '/SearchEmail/'+Email,{
    });
  }

  getSearchPhone(Phone: string){
    //console.log("GatPart "+Phone);
    return this.http.get(this.API + '/SearchPhone/'+Phone,{
    });
  }

  getJobSelect(idJob: number){
    return this.http.get(this.API + '/SearchJob/'+idJob,{
    });
  }

  getNameAndJob(idJob: number, name: string){
    return this.http.get(this.API + '/SearchJobAndName/'+idJob+"/"+name,{
    });
  }

  getEmailAndJob(idJob: number, email: string){
    return this.http.get(this.API + '/SearchAllJobAndEmail/'+idJob+"/"+email,{
    });
  }

  getPhoneAndJob(idJob: number, phone: string){
    return this.http.get(this.API + '/SearchAllJobAndPhone/'+idJob+"/"+phone,{
    });
  }
}
