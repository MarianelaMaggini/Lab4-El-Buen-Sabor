import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ChangePasswordDto } from '../models/change-password-dto';
import { EmailValuesDto } from '../models/email-values-dto';

@Injectable({
  providedIn: 'root'
})
export class EmailPasswordService {

  changePasswordUrl = environment.changePasswordUrl;
  constructor(private http: HttpClient) { }

  sendEmail(emailValuesDto: EmailValuesDto): Observable<any>{
    return this.http.post<any>(this.changePasswordUrl + '/send-email-password', emailValuesDto);
  }
  changePassword(changePasswordDto: ChangePasswordDto): Observable<any>{
    return this.http.post<any>(this.changePasswordUrl + '/change-password', changePasswordDto);
  }
}
