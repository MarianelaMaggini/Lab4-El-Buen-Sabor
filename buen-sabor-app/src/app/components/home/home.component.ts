import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  animate: string = "animate__bounceOutRight"; 
  slow: string = "animate__slow"
  constructor() { }

  ngOnInit(): void {
  }
  over(){
    const element = document.getElementById("delivery");
    element!.classList.add("animate__animated", this.animate);
    element!.classList.add("animate__animated", this.slow);
    element!.addEventListener('animationend', () => {
      element!.classList.remove("animate__animated", this.animate);
    })
  }
}
