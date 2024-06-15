export class User {

    id: number;
    name: string;
    email: string;
    password: string;
    type: string;

    constructor(id: number, name: string, email:string, password:string, type: string){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
      }
}
