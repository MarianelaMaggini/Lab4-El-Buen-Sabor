import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { EmailValuesDto } from 'src/app/models/email-values-dto';
import { EmailPasswordService } from 'src/app/services/email-password.service';

@Component({
  selector: 'app-send-email',
  templateUrl: './send-email.component.html',
  styleUrls: ['./send-email.component.css']
})
export class SendEmailComponent implements OnInit {

  mailTo:string;
  emailValuesDto: EmailValuesDto;
  constructor(
    private emailPasswordService: EmailPasswordService, 
    private toastrService: ToastrService
    ) { }

  ngOnInit(): void {
  }

  sendEmail(): void{
    this.emailValuesDto = new EmailValuesDto(this.mailTo);
    this.emailPasswordService.sendEmail(this.emailValuesDto).subscribe((data) => {
      this.toastrService.success(data.message, 'OK', {
        timeOut: 3000, 
        positionClass:'toast-top-center'
      })
    },
    err => {
      this.toastrService.error(err.error.message, 'FAIL', {
        timeOut: 3000, 
        positionClass:'toast-top-center'
      })
    });
  }

}
