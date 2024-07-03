import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './pages/index/index.component';
import { UtilisateurComponent } from './pages/utilisateur/utilisateur.component';
import { FilesComponent } from './pages/files/files.component';
  import { AuthGuard } from './auth/auth.guard';
import { LoginComponent } from './pages/login/login.component';
 import { FileArchiverComponent } from './pages/file-archiver/file-archiver.component';
import { RegisterComponent } from './pages/register/register.component';
import { ListAutorisationComponent } from './pages/list-autorisation/list-autorisation.component';
import { ChatComponent } from './pages/chat/chat.component';
import { AuthRedirectGuard } from './auth/AuthRedirectGuard ';
 

const routes: Routes = [
   { path: "home", component: IndexComponent ,canActivate: [AuthGuard]  },
    { path: "chat", component: ChatComponent ,canActivate: [AuthGuard] },
 
 { path: "filesArchiver", component: FileArchiverComponent ,canActivate: [AuthGuard] },
   { path: "autorisation", component: ListAutorisationComponent ,canActivate: [AuthGuard] },
  { path: "employeur", component: UtilisateurComponent ,canActivate: [AuthGuard] },
  { path: "files", component: FilesComponent ,canActivate: [AuthGuard] },
    { path: "login", component: LoginComponent ,canActivate: [AuthRedirectGuard] },
  { path: "register", component: RegisterComponent  ,canActivate: [AuthRedirectGuard]},
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
