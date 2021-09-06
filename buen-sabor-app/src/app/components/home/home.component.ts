import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isLogged = false;
  isAdmin = false;
  animate: string = "animate__bounceOutRight"; 
  slow: string = "animate__slow"
  day: string;
  hour: any;
  minute: string;
  second: string;
  ampm: string;
  constructor(
    private tokenService: TokenService,
    ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    setInterval(() => {
      const date = new Date();
      this.updateDate(date);
    }, 1000)
  }
  over(){
    const element = document.getElementById("delivery");
    element!.classList.add("animate__animated", this.animate);
    element!.classList.add("animate__animated", this.slow);
    element!.addEventListener('animationend', () => {
      element!.classList.remove("animate__animated", this.animate);
    })
  }

  updateDate(date: Date):void{
    const hours = date.getHours();
    this.ampm = hours >= 12 ? 'PM' : 'AM';
    this.hour = hours;
    this.hour = this.hour < 10 ? '0' + this.hour : this.hour;

    const minutes = date.getMinutes();
    this.minute = minutes < 10 ? '0' + minutes : minutes.toString();

    const seconds = date.getSeconds();
    this.second = seconds < 10 ? '0' + seconds : seconds.toString();

  }
}
