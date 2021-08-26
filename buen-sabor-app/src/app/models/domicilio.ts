import { Usuario } from "./usuario";

export interface Domicilio {
    id: number;
    calle: string;
    numero: number;
    localidad: string;
    fechaBaja: Date | null;
    usuario: Usuario;
}
