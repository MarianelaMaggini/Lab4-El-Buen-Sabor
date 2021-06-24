export class NuevoUsuario {
    nombre: string;
    apellido: string;
    telefono: string;
    email: string;
    clave: string;

    constructor(nombre: string, apellido: string, telefono: string, email: string, clave: string){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }
}
