export interface Usuario {
    id:number;
    nombre: string;
    apellido: string;
    telefono: string;
    email: string;
    clave:string;
    enabled:boolean;
    tokenPassword:string | null;
}
