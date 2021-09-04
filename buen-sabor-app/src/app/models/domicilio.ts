import { Localidad } from "./localidad";
import { Usuario } from "./usuario";

export interface Domicilio {
    id: number;
    calle: string;
    numero: number;
    fechaBaja: Date | null;
    usuario: Usuario;
    localidad: Localidad;
}
