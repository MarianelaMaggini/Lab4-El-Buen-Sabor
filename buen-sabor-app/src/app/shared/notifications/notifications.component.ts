import { Component, OnInit, OnDestroy } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';
import { StorageService } from 'src/app/services/storage.service';
@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit, OnDestroy {
  webSocketEndPoint: string = 'http://localhost:8080/ws';
  disabled = true;
  stompClient: any;
  messages: string[];

  constructor(private storageService: StorageService) { }

  ngOnInit(): void {
    if (this.storageService.exist('notification')) {
      this.messages = this.storageService.get('notification')
    }
    //this.connect();
  }
  ngOnDestroy(): void {
    this.disconnect();
   }

  setConnected(connected: boolean) {
    this.disabled = !connected;
    if (connected) {
      this.messages = [];
    }
  }

  connect() {
    const socket = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(socket);
    const _this = this;
    _this.stompClient.connect({},  (frame: any) => {
      this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.stompClient.subscribe('/topic/notification', function(message: any){
        console.log(message.body);
        _this.showMessage(JSON.parse(message.body).message)
      });
    });
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  showMessage(message: string){
    this.messages.push(message);
    this.storageService.set('notifications', this.messages);
  }

  webSocketChange(event: any):void{
    if (event.target.checked) {
      this.connect();      
    }else{
      this.disconnect();
    }
  }

}
