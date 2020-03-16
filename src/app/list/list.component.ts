import { Component, OnInit } from '@angular/core';
import { HiserviceService } from '../service/hiservice.service';
import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { DataSource } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';

import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';

export interface PeriodicElement {

  //จะเอาออกมาแสดงในตาราง
  name: string;
  position: number;
  weight: number;
  symbol: string;
  id:number;
  phone:string;
  email: string;
  jobposition:any;
  detail:string;
  statususer:any;

}
export interface SearchEnd {
  valueNameSearch: string;
  viewSearch: string;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})


export class ListComponent implements OnInit {

  nameregister:any;
  dataSource:any;
  job:any;
  displayedColumns: string[] = ['id', 'date' , 'name', 'email','jobposition','detail','actions','statususer'];
  jobpositions: any ; //
  listdatas: any;
  viewlist: Array<any>;
  searchSelect: string;
  searchSelectId: number;
  searchSelects: any;
  inputsearch: string;
  jobSelect: number;

  searchEnds: SearchEnd[] = [
    {valueNameSearch: 'name', viewSearch: 'ชื่อจริง'},
    {valueNameSearch: 'email', viewSearch: 'อีเมล'},
    {valueNameSearch: 'phone', viewSearch: 'เบอร์โทร'}
  ];




  constructor(private hiserviceService: HiserviceService, private httpClient: HttpClient,private router : Router,
    private dialog: MatDialog) { }


  ngOnInit(): void {
    //เรียกไปใช้แสดง
    this.hiserviceService.getJob().subscribe(data => {
      this.jobpositions = data;
    //  console.log(this.jobpositions);
    });
    this.hiserviceService.getHi().subscribe(data => {
      this.listdatas = data;
    //  console.log(this.listdatas);
    });


    this.getTable();
   // this.getTableJob();
   //this.getSearchJobs();
  }



////////////////////////////////
  getTable(){
    this.hiserviceService.getHi().subscribe(data => {
      this.nameregister = data;
    //  console.log(this.nameregister);

      this.dataSource = new MatTableDataSource(this.nameregister);
    //  console.log(this.dataSource);
    })
  }




////////////////////////////////
  //ค้นหา
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

////////////////////////////////
  onDownload(row){
   // console.log("ดู row"+row.toString());
    }

////////////////////////////////
    //กดดูview
  onView(row){
    this.hiserviceService.changeMessage(row);
    this.hiserviceService.currentMessage.subscribe();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "30%";

    this.dialog.open(ReportDialogComponent,dialogConfig);
  }

////////////////////////////////
  onDelete(row){
    this.hiserviceService.deleteName(row.id).subscribe(
      data => {
     //   console.log('ok');
        alert("ทำการลบแล้วเรียบร้อย");
      },
      error => {
      //  console.log('error');
      }
    );
    this.ngOnInit();
   // console.log("ดู row "+row.id);

    }

    onSearch(){
    // console.log("aaaaa"+this.jobSelect);
      //this.hiserviceService.getSearchName(this.inputsearch);
    //  console.log(this.hiserviceService.getSearchName(this.searchSelect));

     // this.hiserviceService.getSearchName().subscribe(data => {
     //   this.searchSelects = data;
    //console.log(this.inputsearch);
    //  });
    //this.SearchNames();
    if(this.searchSelect === "name" && this.jobSelect === undefined){
      this.hiserviceService.getSearchName(this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }
    else  if(this.searchSelect === "email" && this.jobSelect === undefined){
      this.hiserviceService.getSearchEmail(this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }
    else  if(this.searchSelect === "phone" && this.jobSelect === undefined){
      this.hiserviceService.getSearchPhone(this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }
    else if(this.jobSelect !== undefined && this.searchSelect === undefined ){
      this.hiserviceService.getJobSelect(this.jobSelect).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }
    else if(this.jobSelect !== undefined && this.searchSelect === "name" && this.inputsearch !== ""){
      //console.log("pppppppppppppppppp"+this.inputsearch);
      this.hiserviceService.getNameAndJob(this.jobSelect,this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }

    else if(this.jobSelect !== undefined && this.searchSelect === "email" && this.inputsearch !== ""){
      this.hiserviceService.getEmailAndJob(this.jobSelect,this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }

    else if(this.jobSelect !== undefined && this.searchSelect === "phone" && this.inputsearch !== ""){
      this.hiserviceService.getPhoneAndJob(this.jobSelect,this.inputsearch).subscribe(data => {
      this.searchSelects = data;
      console.log(this.searchSelects);
      this.dataSource = this.searchSelects;
      });
    }

    else if(this.inputsearch === ""){
      console.log("ooooooooooooooooooooooooo");
      this.hiserviceService.getHi().subscribe(data => {
        this.searchSelects = data;
        this.dataSource = this.searchSelects;
        });
    }



    }

   /* SearchNames(){
      this.hiserviceService.getSearchName(this.inputsearch)  //จากhtml
      .subscribe(
        data => {

          console.log(this.inputsearch);
        },
        error => {
       }
      );

  }*/

}
