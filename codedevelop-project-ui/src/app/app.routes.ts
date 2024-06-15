import { Routes } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { AuthGuard } from './services/auth-guard.service';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    {path: "login", component: LoginFormComponent},
    {path: "dashboard", component: UserListComponent, canActivate: [AuthGuard]}
];
