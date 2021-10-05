import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ChangePasswordDto } from 'src/app/models/change-password-dto';
import { EmailPasswordService } from 'src/app/services/email-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  password:string;
  confirmPassword: string;
  tokenPassword: string;
  changePasswordDto: ChangePasswordDto;
  constructor(
    private emailPasswordService: EmailPasswordService, 
    private toastrService: ToastrService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  ngOnInit(): void {
  }

  changePassword():void{
    if (this.password !== this.confirmPassword) {
      this.toastrService.error('Las contraseñas no son iguales', 'FAIL', {
        timeOut: 3000, 
        positionClass:'toast-top-center'
      });
      return;
    }
    this.tokenPassword = this.route.snapshot.params.tokenPassword;
    this.changePasswordDto = new ChangePasswordDto(this.password, this.confirmPassword, this.tokenPassword);
    this.emailPasswordService.changePassword(this.changePasswordDto).subscribe(
      (data) => {
        this.toastrService.success(data.message, 'OK', {
          timeOut: 3000, 
          positionClass:'toast-top-center'
        });
        this.router.navigate(['/login']);
      },
      err => {
        this.toastrService.error(err.error.message, 'FAIL', {
          timeOut: 3000, 
          positionClass:'toast-top-center'
        })
      });
  }

}
