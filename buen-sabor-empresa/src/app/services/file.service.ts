import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class FileService {

    private fileUrl = environment.fileUrl;
    private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

    constructor(private http: HttpClient) { }

    // Guardar imágen en el servidor 
    public upload(imagen: File): Observable<any> {
        const formData = new FormData();
        formData.append('file', imagen);
        return this.http.post<any>(this.fileUrl, formData);
    }

}