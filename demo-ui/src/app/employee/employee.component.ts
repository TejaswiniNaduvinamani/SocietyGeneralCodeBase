import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeDetailService } from '../shared/employee-detail.service';
import { EmployeeDetail, EmployeeDetailWrapper } from './employee-detail';
import { ToasterService, ToasterConfig } from 'angular2-toaster';
import { Calendar } from 'primeng/primeng';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  public employeeForm: FormGroup = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    gender: ['', Validators.required],
    dob: [''],
    department: ['']
  });

  public toasterconfig: ToasterConfig = new ToasterConfig({
    timeout: 1000
  });

  public employeeDetail = new EmployeeDetail();
  public maxGridSize = 25;
  public employeeDetails = [];

  constructor(private fb: FormBuilder, private employeeService: EmployeeDetailService, private toastr: ToasterService
    ) { }

  ngOnInit() {
  }

  onSubmit() {
    this.createEmployeeDetailModel();
    this.employeeService.registerEmployeeDetails(this.employeeDetail).subscribe((res) => {
      if(res.statusCode==200){
        this.toastr.pop('success', res.statusMsg);
        this.clearForm();
      } else if(res.statusCode==500) {
        this.toastr.pop('error', res.statusMsg);
      }
    });
  }

  getEmployeeDetails(){
    this.employeeService.fetchEmployeeDetails().subscribe( (res) =>{
      if(res.statusCode === '200'){
        this.employeeDetails = res.empDTO;
      }
    });
  }

  updateCalendarUI(calendar: Calendar) {
    calendar.updateUI();
  }

clearForm(){
  this.employeeForm.controls['firstName'].setValue('');
  this.employeeForm.controls['lastName'].setValue('');
  this.employeeForm.controls['department'].setValue('');
  this.employeeForm.controls['dob'].setValue('');
  this.employeeForm.controls['gender'].setValue('');
}

  createEmployeeDetailModel() {
    this.employeeDetail.firstName = this.employeeForm.controls['firstName'].value;
    this.employeeDetail.lastName = this.employeeForm.controls['lastName'].value;
    this.employeeDetail.gender = this.employeeForm.controls['gender'].value;
    this.employeeDetail.dateOfBirth = this.employeeForm.controls['dob'].value;
    this.employeeDetail.department = this.employeeForm.controls['department'].value;
  }


}
