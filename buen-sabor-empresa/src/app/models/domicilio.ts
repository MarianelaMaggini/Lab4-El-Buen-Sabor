import { Usuario } from "./usuario";

export interface Domicilio {
    
    id: number;
    calle: string;
    localidad: string;
    numero: number;
    usuario: Usuario;
}