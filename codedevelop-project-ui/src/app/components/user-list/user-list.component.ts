import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from "../../services/user.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService,
    private router: Router) { }
  
    ngOnInit(): void {
      this.getUsers();
    }
  
    private getUsers(){
      this.userService.findAll().subscribe(data => {
        this.users = data;
      });
    }
  
    findById(id: number){
      this.router.navigate(['user-details', id]);
    }
  
    update(id: number){
      this.router.navigate(['update-user', id]);
    }
  
    delete(id: number){
      this.userService.delete(id).subscribe( data => {
        console.log(data);
        this.getUsers();
      })
    }
}
