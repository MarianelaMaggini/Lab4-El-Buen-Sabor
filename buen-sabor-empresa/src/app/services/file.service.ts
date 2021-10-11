import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import * as FileSaver from 'file-saver';  
import * as XLSX from 'xlsx';  
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';  
const EXCEL_EXTENSION = '.xlsx';

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

    // Exportar datos de un Json a Excel
    public exportAsExcelFile(json: any[], excelFileName: string): void {  
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);  
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };  
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });  
        this.saveAsExcelFile(excelBuffer, excelFileName);  
    }  

    // Descargar archivo Excel
    private saveAsExcelFile(buffer: any, fileName: string): void {  
        const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});  
        FileSaver.saveAs(data, fileName + EXCEL_EXTENSION);  
    } 

}