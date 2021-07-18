import { Usuario } from "./usuario";

export class Domicilio {
    id: number;
    calle: string;
    numero: number;
    localidad: string;
    fechaBaja: Date;
    usuario: Usuario;
}
