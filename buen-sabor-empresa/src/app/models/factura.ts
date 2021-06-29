export interface Factura {
    
    numeroFactura: number;
    fecha: Date;
    montoDescuento: number;
    formaPago: string;
    totalVenta: number;
    totalCosto: number;
}