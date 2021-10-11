export class NuevoUsuario {
    nombre: string;
    apellido: string;
    telefono: string;
    email: string;
    clave: string;
    roles: string[];

    constructor(nombre: string, apellido: string, telefono: string, email: string, clave: string, roles:string[]){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.roles = roles;
    }
}
