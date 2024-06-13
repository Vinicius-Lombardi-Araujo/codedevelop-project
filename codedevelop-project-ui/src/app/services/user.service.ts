import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  http = inject(HttpClient);

  API = "http://localhost:8080/user";

  constructor() { }

  listAll(): Observable<User[]>{
    return this.http.get<User[]>(this.API+"/listAll");
  }

  delete(id: number): Observable<string>{
    return this.http.delete<string>(this.API+"/delete/"+id, {responseType: 'text' as 'json'});
  }

  save(user: User): Observable<string>{
    return this.http.post<string>(this.API+"/save", user, {responseType: 'text' as 'json'});
  }

  update(user: User, id: number): Observable<string>{
    return this.http.put<string>(this.API+"/update/"+id, user, {responseType: 'text' as 'json'});
  }

  findById(id: number): Observable<User>{
    return this.http.get<User>(this.API+"/findById/"+id);
  }

}