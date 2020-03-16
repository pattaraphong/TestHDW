import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { HiserviceService } from '../service/hiservice.service';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.css']
})
export class ReportDialogComponent implements OnInit {

  message: any;
  subscription: Subscription;

  constructor(public dialogRef: MatDialogRef<ReportDialogComponent>,private hiserviceService: HiserviceService) {

  }

  ngOnInit() {
    this.hiserviceService.currentMessage.subscribe(message => this.message = message)
    console.log(this.message);
  }


  onClose() {
    this.dialogRef.close();
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
