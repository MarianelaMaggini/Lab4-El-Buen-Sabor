import { Usuario } from "./usuario";

export interface DomicilioForm {
    id: number;
    calle: string;
    numero: number;
    fechaBaja: Date | null;
    usuario: Usuario;
    idLocalidad: number;
}
