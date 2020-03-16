import { Component, AfterViewInit } from '@angular/core';
import { HiserviceService } from '../service/hiservice.service';
import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { stringify } from 'querystring';


@Component({
  selector: 'app-hi',
  templateUrl: './hi.component.html',
  styleUrls: ['./hi.component.css']
})

export class HiComponent implements AfterViewInit {

//ป้อนค่าจากUI
  inputName: string;
  inputEmail: string;
  inputPhone: number;
  job: number = 1;
  detail: string;
  status: number = 1;
  accept: boolean;



  constructor(private hiserviceService: HiserviceService , private httpClient: HttpClient,private router : Router) { }

  ngAfterViewInit(){
   // this.relatedService.getAllActivity().subscribe(data => {
   //   this.names = data;
   //   console.log(this.names);
   // });

  }


  Save(){


    if ((this.inputName == null)||((this.inputName >= '1')&&(this.inputName <= '9'))) { //จากhtml
    alert('กรุณากรอกชื่อ ที่เป็นตัวอักษร ');
            }
    else if ((this.inputEmail == null)||((this.inputEmail >= 'ก')&&(this.inputEmail <= 'ฮ'))){
      alert('กรุณากรอกอีเมล');
    }
    else if ((this.inputPhone == null)||((this.inputPhone <= 9)&&(this.inputPhone >= 11)))  {
      alert('กรุณากรอกเบอร์โทร');
    }
    else{

      this.save_func();
     // alert('ยืนยันการบันทึก');
    }
      }


 save_func(){
  this.hiserviceService.addName(this.inputName, this.inputEmail, this.inputPhone, this.job, this.detail, this.status)  //จากhtml
  .subscribe(
    data => {
      this.inputName = '';
      this.job = 1;
      this.status = 1;
      console.log('ok');
      alert('บันทึกสำเร็จ');
    },
    error => {
       alert('บันทึกไม่สำเร็จ');
        console.log(this.inputName);  //จากhtml
        console.log(this.inputEmail);
        console.log(this.inputPhone);
        console.log(this.job);
        console.log(this.detail);
        console.log(this.status);
        console.log('Rrror', error);

   }
  );


}





}
