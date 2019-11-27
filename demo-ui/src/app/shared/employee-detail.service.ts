import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../src/environments/environment.prod';
import { EmployeeDetail, EmployeeDetailWrapper } from '../employee/employee-detail';
import { catchError, tap, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeDetailService {

  private _fetchEmployeeDetails = environment.fetchEmployeeDetails ;

  private _registerEmployee = environment.registerEmployee ;

  constructor(private _http: HttpClient) { }

  public fetchEmployeeDetails(): Observable<EmployeeDetailWrapper> {

    return this._http.get(this._fetchEmployeeDetails).pipe(map((res: EmployeeDetailWrapper) => res));
    }

    public registerEmployeeDetails(emloyeeDetail: EmployeeDetail): Observable<any> {

      return this._http.post(this._registerEmployee, emloyeeDetail).pipe(map((res: any) => res));
    }
}
